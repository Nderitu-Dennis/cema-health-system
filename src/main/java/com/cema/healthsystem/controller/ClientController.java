package com.cema.healthsystem.controller;

import com.cema.healthsystem.entity.Client;
import com.cema.healthsystem.entity.Enrollment;
import com.cema.healthsystem.service.ClientService;
import com.cema.healthsystem.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/clients")  // Ensure this is consistent for all routes
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
        Client savedClient = clientService.registerClient(client);  // Assuming this saves the client
        model.addAttribute("client", savedClient);
        return "redirect:/clients/profile/" + savedClient.getId(); // Fix URL path here
    }

    // show client profile
    @GetMapping("/profile/{id}")
    public String viewClientProfile(@PathVariable Long id, Model model) {
        Optional<Client> clientOptional = clientService.getClientProfile(id);

        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            List<Enrollment> enrollments = enrollmentService.getEnrollmentsByClient(client);
            model.addAttribute("client", client);
            model.addAttribute("enrollments", enrollments);
            return "client/profile";
        } else {
            return "error/404";  // Make sure this page exists!
        }
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

        // Prioritize email, then phoneNumber
        if (email != null && !email.isEmpty()) {
            clientOptional = clientService.findClientByEmail(email);
        } else if (phoneNumber != null && !phoneNumber.isEmpty()) {
            clientOptional = clientService.findClientByPhoneNumber(phoneNumber);
        }

        if (clientOptional.isPresent()) {
            return "redirect:/clients/profile/" + clientOptional.get().getId();  // Fix URL path here
        } else {
            model.addAttribute("notFound", true);
            return "client/search";
        }
    }
}
