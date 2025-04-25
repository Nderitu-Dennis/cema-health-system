package com.cema.healthsystem.repository;

import com.cema.healthsystem.entity.HealthProgram;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthProgramRepository extends JpaRepository<HealthProgram, Long> {
}
