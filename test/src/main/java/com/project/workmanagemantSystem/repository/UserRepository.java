package com.project.workmanagemantSystem.repository;

import com.project.workmanagemantSystem.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);

 @Query(
         value = "select * from \"user\" where email=:email AND status='VERIFIED'" ,nativeQuery = true
 )
 Optional<User>  findByUsersEmail(@Param("email") String email);
}
