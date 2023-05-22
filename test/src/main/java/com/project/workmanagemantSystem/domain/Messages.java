package com.project.workmanagemantSystem.domain;

import com.fasterxml.jackson.databind.JsonNode;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    private String message;
    private UUID senderId;
    private UUID ReceiverId;
    private Boolean IsBookmarked;
    private LocalDateTime sendOn;
}
