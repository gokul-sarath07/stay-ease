package com.crio.stayEase.entity;

import com.crio.stayEase.dto.CreateHotel;
import com.crio.stayEase.dto.UpdateHotel;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "Hotels")
@NoArgsConstructor
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String hotelName;

    private String location;

    private String description;

    private int numberOfAvailableRooms;

    public Hotel(CreateHotel createHotel) {
        setHotelName(createHotel.getHotelName());
        setDescription(createHotel.getDescription());
        setLocation(createHotel.getLocation());
        setNumberOfAvailableRooms(createHotel.getNumberOfAvailableRooms());
    }

    public Hotel(UpdateHotel updateHotel) {
        if (updateHotel != null) {
            if (updateHotel.getHotelName() != null) {
                setHotelName(updateHotel.getHotelName());
            }
            if (updateHotel.getDescription() != null) {
                setDescription(updateHotel.getDescription());
            }
            if (updateHotel.getLocation() != null) {
                setLocation(updateHotel.getLocation());
            }
            if (updateHotel.getNumberOfAvailableRooms() != null) {
                setNumberOfAvailableRooms(updateHotel.getNumberOfAvailableRooms());
            }
        }
    }

    public boolean isRoomsAvailable() {
        return numberOfAvailableRooms > 0;
    }
}
