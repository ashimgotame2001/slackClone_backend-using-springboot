package com.project.workmanagemantSystem.domain;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "sections")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BoardSection {
    @Id
    private UUID id;
    private String name;
    private Long numberOfCards;
    @OneToMany
    private List<Card> cards = new ArrayList<>();
}
