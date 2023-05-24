package com.project.workmanagemantSystem.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "client")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Client {
    @Id
    private UUID id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String owner;
    private String status;
    private String OTP;
    private LocalDateTime startedAt;
    private LocalDateTime expiredAt;

}
