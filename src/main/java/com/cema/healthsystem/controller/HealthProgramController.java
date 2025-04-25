package com.cema.healthsystem.controller;

import com.cema.healthsystem.entity.HealthProgram;
import com.cema.healthsystem.repository.HealthProgramRepository;
import com.cema.healthsystem.service.HealthProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/programs")
public class HealthProgramController {

    private final HealthProgramService healthProgramService;

    @Autowired
    public HealthProgramController(HealthProgramService healthProgramService) {
        this.healthProgramService = healthProgramService;
    }

    // form to create a new health program
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("program", new HealthProgram());
        return "program/create";
    }

    // form submission for creating new health program above
    @PostMapping("/create")
    public String createProgram(@ModelAttribute HealthProgram program) {
        healthProgramService.createHealthProgram(program);
        return "redirect:/programs/all-programs";
    }

    // list all health programs
    @GetMapping("/all-programs")
    public String listAllPrograms(Model model) {
        List<HealthProgram> programs = healthProgramService.getAllHealthPrograms();
        model.addAttribute("programs", programs);
        return "program/all-programs";
    }
}





