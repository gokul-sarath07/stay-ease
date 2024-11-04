package com.crio.stayEase.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomBooking {

    @Nullable
    @Email(message = "Secondary user email is invalid.")
    private String secondaryUserEmail;
}
