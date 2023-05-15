package com.project.workmanagemantSystem.domain;

import com.fasterxml.jackson.databind.JsonNode;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Messages")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Messages {
    @Id
    private UUID id;
    private String messages;
    @OneToOne
    private User senderId;
    private UUID ReceiverId;
    private Boolean IsBookmarked;

}
