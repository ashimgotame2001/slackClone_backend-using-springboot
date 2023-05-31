package com.project.workmanagemantSystem.repository;

import com.project.workmanagemantSystem.domain.Members;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface MemberRepository extends JpaRepository<Members, UUID> {

    Optional<Members> findByEmail(String email);
}
