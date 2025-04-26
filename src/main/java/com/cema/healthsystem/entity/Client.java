package com.cema.healthsystem.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity(name="clients")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    @Id
    @Column(name="client_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   @Column(name="full_name")
    private String fullName;

    private String gender;

    @Column(name="phone_number")
    private String phoneNumber;

    private String email;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Enrollment> enrollments;
}
