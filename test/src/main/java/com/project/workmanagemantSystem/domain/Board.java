package com.project.workmanagemantSystem.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Board")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Board {
    @Id
    private UUID id;
    private String name;
    private LocalDateTime createdAt;
    @OneToOne
    private User createdBy;
    @OneToOne
    private Channels channel;
    @OneToMany
    private List<BoardSection> sections = new ArrayList<>();
}
