package com.crio.stayEase.service;

import com.crio.stayEase.dto.CreateHotel;
import com.crio.stayEase.dto.UpdateHotel;
import com.crio.stayEase.entity.Hotel;

import java.util.List;

public interface HotelService {
    List<Hotel> getAllHotels();
    Hotel createHotel(CreateHotel createHotel);
    Hotel updateHotel(Long hotelId, UpdateHotel updateHotel);
    String deleteHotel(Long hotelId);
    Hotel findByHotelId(Long hotelId);
    Hotel saveHotel(Hotel hotel);
}
