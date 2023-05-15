package com.project.workmanagemantSystem.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "Channels")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Channels {
    @Id
    private UUID id;
    private String name;
    @ManyToMany
    private List<User> members;
    @ManyToMany
    private List<Messages> messages;

    private String status;
}
