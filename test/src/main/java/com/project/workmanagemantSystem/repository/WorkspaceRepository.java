package com.project.workmanagemantSystem.repository;

import com.project.workmanagemantSystem.domain.Channels;
import com.project.workmanagemantSystem.domain.WorkSpace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface WorkspaceRepository extends JpaRepository<WorkSpace, UUID> {

    Optional<WorkSpace> findById(UUID uuid);

//    List<Channels> getListOfChannelsBasedOnWorkSpaceCode(@Param("code")UUID code);
}
