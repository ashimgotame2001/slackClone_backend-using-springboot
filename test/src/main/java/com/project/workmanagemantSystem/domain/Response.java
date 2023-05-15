package com.project.workmanagemantSystem.domain;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Response {

    private HttpStatus status;
    private String message;

}
