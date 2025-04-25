package com.cema.healthsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity(name="programs")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class HealthProgram {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "program_id")
    private Long id;

   @Column(name = "program_name")
   private String programName;

   @Column(name = "program_description")
   private String programDescription;

    @OneToMany(mappedBy = "program", cascade = CascadeType.ALL)
    private Set<Enrollment> enrollments;


}
