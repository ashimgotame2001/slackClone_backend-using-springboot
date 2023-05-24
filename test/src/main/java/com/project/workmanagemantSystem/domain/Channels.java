package com.project.workmanagemantSystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(value = {"members","messages"})
public class Channels {
    @Id
    private UUID id;
    private String name;
    private String channelType;
    @ManyToMany
    private List<Members> members;
    @ManyToMany
    private List<Messages> messages;

    private String status;
}
