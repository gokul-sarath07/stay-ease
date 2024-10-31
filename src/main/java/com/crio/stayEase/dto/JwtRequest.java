package com.crio.stayEase.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JwtRequest {

    @NotBlank(message = "User email is required")
    @Email(message = "Email format is invalid.")
    private String username;

    @NotBlank(message = "password is required")
    private String password;
}
