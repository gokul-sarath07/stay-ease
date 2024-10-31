package com.crio.stayEase.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
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
