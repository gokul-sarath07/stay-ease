package com.crio.stayEase.service;

import com.crio.stayEase.dto.RoomBooking;
import com.crio.stayEase.entity.Booking;

import java.util.List;

public interface BookingService {
    Booking bookRoom(Long hotelId, String primaryEmail, RoomBooking roomBooking);
    String cancelBooking(Long bookingId);
    List<Booking> getUserBookings(Long userId);
    Booking findByBookingId(Long bookingId);
}
