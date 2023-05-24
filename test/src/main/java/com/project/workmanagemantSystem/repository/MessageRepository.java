package com.project.workmanagemantSystem.repository;

import com.project.workmanagemantSystem.domain.Messages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MessageRepository extends JpaRepository<Messages, UUID> {
}
