package com.crio.stayEase.dto;

import jakarta.annotation.Nullable;
import lombok.Data;

@Data
public class UpdateHotel {
    @Nullable
    private String hotelName;

    @Nullable
    private String location;

    @Nullable
    private String description;

    @Nullable
    private Integer numberOfAvailableRooms;
}
