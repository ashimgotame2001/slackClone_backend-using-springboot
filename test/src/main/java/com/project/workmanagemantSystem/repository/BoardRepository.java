package com.project.workmanagemantSystem.repository;

import com.project.workmanagemantSystem.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface BoardRepository extends JpaRepository<Board, UUID> {
    @Query(
            "select ch from Board as ch where ch.channel=:code"
    )
    Optional<Board> findByChannelId(@Param("code") UUID code);
}
