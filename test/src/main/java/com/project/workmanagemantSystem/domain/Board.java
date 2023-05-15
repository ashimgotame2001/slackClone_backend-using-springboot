package com.project.workmanagemantSystem.domain;

import lombok.*;

import javax.persistence.*;
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
    private String Status;
    private Integer numberOfLane;
    @OneToOne
    private Channels channel;
    @OneToMany
    private List<Card> cards = new ArrayList<>();
}
