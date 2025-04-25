package com.cema.healthsystem.repository;

import com.cema.healthsystem.entity.Client;
import com.cema.healthsystem.entity.Enrollment;
import com.cema.healthsystem.entity.HealthProgram;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    // get all enrollments by a client
    List<Enrollment> findByClient(Client client);

    // get all enrollments by program
    List<Enrollment> findByProgram(HealthProgram program);

    // prevents multiple enrollments of the same client in the same program
    boolean existsByClientAndProgram(Client client, HealthProgram program);

}
