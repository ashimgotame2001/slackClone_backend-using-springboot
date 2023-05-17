package com.project.workmanagemantSystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "Workspace")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
@JsonIgnoreProperties(value = "clientCode")

public class WorkSpace {
    @Id
    private UUID id;
    @OneToMany
    private List<Channels> Channels;
    @OneToMany
    private List<Members> members;
    private String name;
    @ManyToOne
    private Client clientCode;

}
