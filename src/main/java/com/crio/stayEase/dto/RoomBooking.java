package com.crio.stayEase.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class RoomBooking {

    @Nullable
    @Email(message = "Secondary user email is invalid.")
    private String secondaryUserEmail;
}
