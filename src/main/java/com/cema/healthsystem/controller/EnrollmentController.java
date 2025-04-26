package com.cema.healthsystem.controller;

import com.cema.healthsystem.entity.Client;
import com.cema.healthsystem.entity.HealthProgram;
import com.cema.healthsystem.service.ClientService;
import com.cema.healthsystem.service.EnrollmentService;
import com.cema.healthsystem.service.HealthProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/enrollments")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;
    private final ClientService clientService;
    private final HealthProgramService healthProgramService;

    @Autowired
    public EnrollmentController(EnrollmentService enrollmentService, ClientService clientService, HealthProgramService healthProgramService){
        this.enrollmentService = enrollmentService;
        this.clientService = clientService;
        this.healthProgramService=healthProgramService;

    }

    // form to enroll a client
    @GetMapping("/enroll")
    public String showEnrollmentForm(Model model) {
        model.addAttribute("clients", clientService.getAllClients());
        model.addAttribute("programs", healthProgramService.getAllHealthPrograms());
        return "enrollment/enroll";
    }

    // enrollment submission
    @PostMapping("/enroll")
    public String enrollClient(@RequestParam Long clientId,
                               @RequestParam Long programId,
                               Model model) {
        Optional<Client> clientOptional = clientService.getClientProfile(clientId);
        Optional<HealthProgram> programOptional = healthProgramService.getHealthProgramById(programId);

        if (clientOptional.isPresent() && programOptional.isPresent()) {
            Client client = clientOptional.get();
            HealthProgram program = programOptional.get();

            // Check if the client is already enrolled in the program
            boolean alreadyEnrolled = client.getEnrollments().stream()
                    .anyMatch(enrollment -> enrollment.getProgram().equals(program));

            if (alreadyEnrolled) {
                model.addAttribute("error", "Client is already enrolled in this program.");
                model.addAttribute("selectedClient", client);
                model.addAttribute("selectedProgram", program);
                model.addAttribute("clients", clientService.getAllClients());
                model.addAttribute("programs", healthProgramService.getAllHealthPrograms());
                return "enrollment/enroll"; // Show the enrollment form again
            }

            // Enroll client in the selected program
            enrollmentService.enrollClientInProgram(client, program);

            // After successful enrollment, redirect to the success page
            model.addAttribute("client", client);
            return "enrollment/enroll-success";
        } else {
            model.addAttribute("error", "Invalid client or program");
            return "enrollment/enroll";
        }
    }

}
