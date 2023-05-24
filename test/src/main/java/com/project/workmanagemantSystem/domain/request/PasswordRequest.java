package com.project.workmanagemantSystem.domain.request;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class PasswordRequest {
    @NotNull
    private String email;
    private String oldPassword;
    @NotNull
    private String newPassword;
}
