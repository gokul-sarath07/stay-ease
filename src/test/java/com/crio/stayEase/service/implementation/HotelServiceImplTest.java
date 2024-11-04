package com.crio.stayEase.service.implementation;

import com.crio.stayEase.dto.CreateHotel;
import com.crio.stayEase.dto.UpdateHotel;
import com.crio.stayEase.entity.Hotel;
import com.crio.stayEase.repository.HotelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HotelServiceImplTest {
    @Mock
    private HotelRepository hotelRepository;

    @InjectMocks
    private HotelServiceImpl hotelService;

    private CreateHotel createHotel;
    private UpdateHotel updateHotel;
    private Hotel hotel;

    @BeforeEach
    void setUp() {
        createHotel = new CreateHotel("hotelName", "location", "description", 1);
        updateHotel = new UpdateHotel("hotelName", "location", "description", 1);
        hotel = new Hotel(createHotel);
        hotel.setId(1L);
    }

    @Test
    void getAllHotels_ShouldReturnListOfHotels() {
        List<Hotel> hotels = List.of(hotel);
        when(hotelRepository.findAll()).thenReturn(hotels);

        List<Hotel> result = hotelService.getAllHotels();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(hotelRepository, times(1)).findAll();
    }

    @Test
    void createHotel_ShouldSaveAndReturnHotel_WhenValidCreateHotelIsProvided() {
        when(hotelRepository.save(any(Hotel.class))).thenReturn(hotel);

        Hotel savedHotel = hotelService.createHotel(createHotel);

        assertNotNull(savedHotel);
        assertEquals(1L, savedHotel.getId());
        verify(hotelRepository, times(1)).save(any(Hotel.class));
    }

    @Test
    void updateHotel_ShouldUpdateAndReturnHotel_WhenHotelExists() {
        when(hotelRepository.findById(1L)).thenReturn(Optional.of(hotel));
        when(hotelRepository.save(any(Hotel.class))).thenReturn(hotel);

        Hotel updatedHotel = hotelService.updateHotel(1L, updateHotel);

        assertNotNull(updatedHotel);
        assertEquals(1L, updatedHotel.getId());
        verify(hotelRepository, times(1)).findById(1L);
        verify(hotelRepository, times(1)).save(any(Hotel.class));
    }
}