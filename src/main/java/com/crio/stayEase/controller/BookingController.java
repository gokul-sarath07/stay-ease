package com.crio.stayEase.controller;

import com.crio.stayEase.config.JwtUtil;
import com.crio.stayEase.dto.RoomBooking;
import com.crio.stayEase.entity.Booking;
import com.crio.stayEase.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.crio.stayEase.config.PathConstants.*;

@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping(ROOM_BOOKING)
    public ResponseEntity<Booking> bookRoom(@PathVariable("hotelId") Long hotelId,
                                            @Valid @RequestBody RoomBooking roomBooking,
                                            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        String token = authorizationHeader.substring(7);
        String primaryUserEmail = jwtUtil.extractUsername(token);
        Booking booking = bookingService.bookRoom(hotelId, primaryUserEmail, roomBooking);

        return ResponseEntity.ok(booking);
    }

    @DeleteMapping(CANCEL_BOOKING)
    public ResponseEntity<String> cancelBooking(@PathVariable("bookingId") Long bookingId) {
        String status = bookingService.cancelBooking(bookingId);

        return ResponseEntity.ok(status);
    }

    @GetMapping(USER_BOOKINGS)
    public ResponseEntity<List<Booking>> getUserBookings(@PathVariable("userId") Long userId) {
        List<Booking> bookings = bookingService.getUserBookings(userId);

        return ResponseEntity.ok(bookings);
    }

    @GetMapping(GET_BOOKING)
    public ResponseEntity<Booking> getBooking(@PathVariable("bookingId") Long bookingId) {
        Booking booking = bookingService.findByBookingId(bookingId);

        return ResponseEntity.ok(booking);
    }
}
