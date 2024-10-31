package com.crio.stayEase.service;

import com.crio.stayEase.dto.RoomBooking;
import com.crio.stayEase.entity.Booking;
import com.crio.stayEase.entity.Hotel;
import com.crio.stayEase.entity.User;
import com.crio.stayEase.exception.BookingNotFoundException;
import com.crio.stayEase.exception.HotelRoomNotAvailable;
import com.crio.stayEase.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private HotelService hotelService;

    @Override
    public Booking bookRoom(Long hotelId, String primaryEmail, RoomBooking roomBooking) {
        Hotel hotel = getHotelIfRoomAvailable(hotelId);
        User primaryUser = userService.findByEmail(primaryEmail);

        User secondaryUser = null;
        if (roomBooking.getSecondaryUserEmail() != null) {
            secondaryUser = userService.findByEmail(roomBooking.getSecondaryUserEmail());
        }

        Booking booking = createBookingObj(hotel, primaryUser, secondaryUser);
        hotel.bookRoom();

        hotelService.saveHotel(hotel);

        return bookingRepository.save(booking);
    }

    private Hotel getHotelIfRoomAvailable(Long hotelId) {
        Hotel hotel = hotelService.findByHotelId(hotelId);
        if (!hotel.isRoomsAvailable()) {
            throw new HotelRoomNotAvailable("No rooms are currently available for this hotel. Please try later or another hotel.");
        }

        return hotel;
    }

    private Booking createBookingObj(Hotel hotel, User primary, User secondary) {
        Booking booking = new Booking();

        booking.setPrimaryUser(primary);
        booking.setSecondaryUser(secondary);
        booking.setHotel(hotel);

        return booking;
    }

    @Override
    public String cancelBooking(Long bookingId) {
        Booking booking = findByBookingId(bookingId);
        Hotel hotel = booking.getHotel();

        hotel.cancelRoom();
        hotelService.saveHotel(hotel);

        booking.cancelBooking();
        bookingRepository.save(booking);

        return "Booking with id: " + bookingId + " has been canceled.";
    }

    @Override
    public List<Booking> getUserBookings(Long userId) {
        return bookingRepository.findByUserId(userId);
    }

    public Booking findByBookingId(Long bookingId) {
        return bookingRepository.findById(bookingId)
                .orElseThrow(() ->
                new BookingNotFoundException("Booking with id: " + bookingId + " not found."));
    }
}
