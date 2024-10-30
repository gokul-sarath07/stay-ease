package com.crio.stayEase.entity;

import com.crio.stayEase.constants.BookingStatus;
import com.crio.stayEase.dto.RoomBooking;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Bookings")
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "primary_user_id", nullable = false)
    private User primaryUser;

    @ManyToOne
    @JoinColumn(name = "secondary_user_id")
    private User secondaryUser;

    private final LocalDateTime bookingDate = LocalDateTime.now();

    private BookingStatus status = BookingStatus.CONFIRMED;

    public void cancelBooking() {
        status = BookingStatus.CANCELLED;
    }
}
