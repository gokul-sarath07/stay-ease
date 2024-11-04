package com.crio.stayEase.dto;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
