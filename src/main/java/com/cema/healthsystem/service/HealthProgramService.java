package com.cema.healthsystem.service;

import com.cema.healthsystem.entity.HealthProgram;
import com.cema.healthsystem.repository.HealthProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HealthProgramService {

    private final HealthProgramRepository healthProgramRepository;

    @Autowired
    public HealthProgramService(HealthProgramRepository healthProgramRepository){
        this.healthProgramRepository=healthProgramRepository;
    }

    // create a new health program
    public HealthProgram createHealthProgram (HealthProgram healthProgram){
        return healthProgramRepository.save(healthProgram);
    }

    // get all health programs
    public List<HealthProgram> getAllHealthPrograms(){
        return healthProgramRepository.findAll();
    }

    // get health program by id
    public Optional<HealthProgram> getHealthProgramById(Long programId){
        return healthProgramRepository.findById(programId);
    }
}
