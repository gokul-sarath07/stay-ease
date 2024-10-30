package com.crio.stayEase.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Hotels")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String hotelName;

    private String location;

    private String description;

    private int numberOfAvailableRooms;
}
