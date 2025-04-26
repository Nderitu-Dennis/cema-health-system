package com.cema.healthsystem.controller;

import com.cema.healthsystem.entity.Client;
import com.cema.healthsystem.entity.Enrollment;
import com.cema.healthsystem.service.ClientService;
import com.cema.healthsystem.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;
    private final EnrollmentService enrollmentService;

    @Autowired
    public ClientController(ClientService clientService, EnrollmentService enrollmentService) {
        this.clientService = clientService;
        this.enrollmentService = enrollmentService;
    }

    // form to register a client
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("client", new Client());
        return "client/register";
    }

    // handle form submission
    @PostMapping("/register")
    public String registerClient(@ModelAttribute Client client, Model model) {
        Client savedClient = clientService.registerClient(client);
        model.addAttribute("client", savedClient);
        return "redirect:/clients/profile/" + savedClient.getId();
    }

    // API to expose client profile data
    @GetMapping("/api/profile/{id}")
    @ResponseBody
    public Client getClientProfileApi(@PathVariable Long id) {
        return clientService.getClientProfile(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found"));
    }


    // show client profile
    @GetMapping("/profile/{id}")
    public String viewClientProfile(@PathVariable Long id, Model model) {
        Optional<Client> clientOptional = clientService.getClientProfile(id);


        if (clientOptional.isEmpty()) {
            return "redirect:/clients/search";
        }

        Client client = clientOptional.get();
        List<Enrollment> enrollments = enrollmentService.getEnrollmentsByClient(client);
        model.addAttribute("client", client);
        model.addAttribute("enrollments", enrollments);

        if (client.getDateOfBirth() != null) {
            int age = java.time.Period.between(client.getDateOfBirth(), java.time.LocalDate.now()).getYears();
            model.addAttribute("age", age);
        }
        return "client/profile";
    }

    // search client by email or phone
    @GetMapping("/search")
    public String searchClientForm() {
        return "client/search";
    }

    @PostMapping("/search")
    public String searchClient(@RequestParam(required = false) String email,
                               @RequestParam(required = false) String phoneNumber,
                               Model model) {
        Optional<Client> clientOptional = Optional.empty();


        if (email != null && !email.isEmpty()) {
            clientOptional = clientService.findClientByEmail(email);
        } else if (phoneNumber != null && !phoneNumber.isEmpty()) {
            clientOptional = clientService.findClientByPhoneNumber(phoneNumber);
        }

        if (clientOptional.isPresent()) {
            return "redirect:/clients/profile/" + clientOptional.get().getId();
        } else {
            model.addAttribute("notFound", true);
            return "client/search";
        }
    }

    // View all clients
    @GetMapping("/all")
    public String viewAllClients(Model model) {
        List<Client> clients = clientService.getAllClients();
        model.addAttribute("clients", clients);
        return "client/all-clients";
    }

}
