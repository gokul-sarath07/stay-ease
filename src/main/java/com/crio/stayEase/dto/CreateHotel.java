package com.crio.stayEase.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateHotel {
    @NotBlank(message = "Hotel name is required")
    private String hotelName;

    @NotBlank(message = "Location is required")
    private String location;

    @NotBlank(message = "Description is required")
    private String description;

    @Min(value = 1, message = "Please mention the number of available rooms")
    private int numberOfAvailableRooms;
}
