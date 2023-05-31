package com.project.workmanagemantSystem.repository;

import com.project.workmanagemantSystem.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {

   Optional<Client> findById(UUID id);
   Optional<Client> findByOTP(String otpCode);

   Optional<Client> findByEmail(String email);

}
