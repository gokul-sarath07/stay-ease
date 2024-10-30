package com.crio.stayEase.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RoomBooking {

    @NotBlank(message = "Primary user email is required.")
    @Email(message = "Primary user email is invalid.")
    private String primaryUserEmail;

    @Nullable
    @Email(message = "Secondary user email is invalid.")
    private String secondaryUserEmail;
}
