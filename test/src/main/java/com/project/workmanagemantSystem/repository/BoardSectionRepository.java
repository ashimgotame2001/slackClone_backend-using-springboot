package com.project.workmanagemantSystem.repository;

import com.project.workmanagemantSystem.domain.BoardSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BoardSectionRepository extends JpaRepository<BoardSection, UUID> {
}
