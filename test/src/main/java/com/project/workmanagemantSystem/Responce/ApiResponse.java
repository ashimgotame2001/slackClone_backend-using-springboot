package com.project.workmanagemantSystem.Responce;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse {

    private HttpStatus status;
    private String message;

}
