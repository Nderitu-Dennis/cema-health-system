package com.cema.healthsystem.service;

import com.cema.healthsystem.entity.Client;
import com.cema.healthsystem.entity.Enrollment;
import com.cema.healthsystem.entity.HealthProgram;
import com.cema.healthsystem.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    @Autowired
    public EnrollmentService(EnrollmentRepository enrollmentRepository){
        this.enrollmentRepository=enrollmentRepository;
    }

    // enroll a client in a program
    public Enrollment enrollClientInProgram (Client client, HealthProgram program) {
        // check if the client is already enrolled in the program
        if (enrollmentRepository.existsByClientAndProgram(client, program)) {
            throw new IllegalArgumentException("client is already enrolled in this program ");
        }

        Enrollment enrollment = new Enrollment();
        enrollment.setClient(client);
        enrollment.setProgram(program);
        enrollment.setEnrollmentDate(java.time.LocalDate.now());

        return enrollmentRepository.save(enrollment);
    }

    // get all enrollments for a client
    public List<Enrollment> getEnrollmentsByClient(Client client){
        return enrollmentRepository.findByClient(client);
    }

    //get all enrollments for a program
    public List<Enrollment> getEnrollmentsByProgram(HealthProgram program){
        return enrollmentRepository.findByProgram(program);
    }
}
