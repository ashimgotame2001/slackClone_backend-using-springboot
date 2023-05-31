package com.project.workmanagemantSystem.domain.request;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class PasswordRequest {
    private String oldPassword;
    @NotNull
    private String newPassword;

    @NotNull
    private UUID userCode;
}
