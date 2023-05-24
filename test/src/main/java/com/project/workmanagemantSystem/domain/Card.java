package com.project.workmanagemantSystem.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(value = {"comments"})
public class Card {
    @Id
    private UUID id;
    private String name;
    private String status;
    private String priority;
    @ManyToOne
    private BoardSection section;
    private String notes;
    @OneToMany
    private List<Members> assignee;
    @OneToMany
    private List<Comment> comments = new ArrayList<>();
}
