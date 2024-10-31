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

    public Hotel(UpdateHotel updateHotel, Hotel oldHotel) {
        if (updateHotel.getHotelName() != null && !updateHotel.getHotelName().isBlank()) {
            setHotelName(updateHotel.getHotelName());
        } else {
            setHotelName(oldHotel.getHotelName());
        }

        if (updateHotel.getDescription() != null && !updateHotel.getDescription().isBlank()) {
            setDescription(updateHotel.getDescription());
        } else {
            setDescription(oldHotel.getDescription());
        }

        if (updateHotel.getLocation() != null && !updateHotel.getLocation().isBlank()) {
            setLocation(updateHotel.getLocation());
        } else {
            setLocation(oldHotel.getLocation());
        }

        if (updateHotel.getNumberOfAvailableRooms() != null) {
            setNumberOfAvailableRooms(updateHotel.getNumberOfAvailableRooms());
        } else {
            setNumberOfAvailableRooms(oldHotel.getNumberOfAvailableRooms());
        }
    }

    public boolean isRoomsAvailable() {
        return numberOfAvailableRooms > 0;
    }

    public void bookRoom() {
        if (isRoomsAvailable()) {
            numberOfAvailableRooms--;
        }
    }

    public void cancelRoom() {
        numberOfAvailableRooms++;
    }
}
