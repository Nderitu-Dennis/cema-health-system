package com.cema.healthsystem.repository;

import com.cema.healthsystem.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    // find client by Email
    Client findByEmail(String email);

    // find client by phone number
    Client findByPhoneNumber( String phoneNumber);
}
