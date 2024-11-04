package com.crio.stayEase.service.implementation;

import com.crio.stayEase.dto.RoomBooking;
import com.crio.stayEase.entity.Booking;
import com.crio.stayEase.entity.Hotel;
import com.crio.stayEase.entity.User;
import com.crio.stayEase.exception.HotelRoomNotAvailable;
import com.crio.stayEase.repository.BookingRepository;
import com.crio.stayEase.service.HotelService;
import com.crio.stayEase.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookingServiceImplTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private UserService userService;

    @Mock
    private HotelService hotelService;

    @InjectMocks
    private BookingServiceImpl bookingService;

    private RoomBooking roomBooking;
    private User primaryUser;
    private User secondaryUser;
    private Hotel hotel;
    private Booking booking;

    @BeforeEach
    void setUp() {
        roomBooking = new RoomBooking("secondary@example.com");
        primaryUser = new User();
        primaryUser.setEmail("primary@example.com");

        secondaryUser = new User();
        secondaryUser.setEmail("secondary@example.com");

        hotel = new Hotel();
        hotel.setId(1L);
        hotel.setNumberOfAvailableRooms(5);

        booking = new Booking();
        booking.setPrimaryUser(primaryUser);
        booking.setSecondaryUser(secondaryUser);
        booking.setHotel(hotel);
        booking.setId(1L);
    }

    @Test
    void bookRoom_ShouldCreateAndSaveBooking_WhenRoomIsAvailable() {
        when(hotelService.findByHotelId(1L)).thenReturn(hotel);
        when(userService.findByEmail("primary@example.com")).thenReturn(primaryUser);
        when(userService.findByEmail("secondary@example.com")).thenReturn(secondaryUser);
        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);

        Booking savedBooking = bookingService.bookRoom(1L, "primary@example.com", roomBooking);

        assertNotNull(savedBooking);
        assertEquals(primaryUser, savedBooking.getPrimaryUser());
        assertEquals(secondaryUser, savedBooking.getSecondaryUser());
        verify(bookingRepository, times(1)).save(any(Booking.class));
        verify(hotelService, times(1)).saveHotel(hotel);
    }

    @Test
    void bookRoom_ShouldThrowHotelRoomNotAvailable_WhenNoRoomsAvailable() {
        hotel.setNumberOfAvailableRooms(0);
        when(hotelService.findByHotelId(1L)).thenReturn(hotel);

        assertThrows(HotelRoomNotAvailable.class, () -> bookingService.bookRoom(1L, "primary@example.com", roomBooking));
    }

    @Test
    void cancelBooking_ShouldCancelBookingAndSaveHotel_WhenBookingExists() {
        when(bookingRepository.findById(1L)).thenReturn(Optional.of(booking));

        String result = bookingService.cancelBooking(1L);

        assertEquals("Booking with id: 1 has been canceled.", result);
        verify(hotelService, times(1)).saveHotel(hotel);
        verify(bookingRepository, times(1)).save(booking);
    }
}