package com.crio.stayEase.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "Bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private List<User> users;

    private final LocalDateTime checkIn = LocalDateTime.now();

    private LocalDateTime checkOut;
}
