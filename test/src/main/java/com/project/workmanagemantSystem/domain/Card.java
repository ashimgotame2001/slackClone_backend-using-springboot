package com.project.workmanagemantSystem.domain;


import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "Card")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Card {
    @Id
    private UUID id;
    private Integer laneNumber;
    private String notes;
    @OneToMany
    private Set<User> assignee = new HashSet<>();
    @OneToMany
    private List<Comment> comments = new ArrayList<>();
}
