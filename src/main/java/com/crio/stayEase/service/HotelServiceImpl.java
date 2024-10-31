package com.crio.stayEase.service;

import com.crio.stayEase.dto.CreateHotel;
import com.crio.stayEase.dto.UpdateHotel;
import com.crio.stayEase.entity.Hotel;
import com.crio.stayEase.exception.HotelNotFoundException;
import com.crio.stayEase.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel createHotel(CreateHotel createHotel) {
        Hotel hotel = new Hotel(createHotel);

        return hotelRepository.save(hotel);
    }

    @Override
    public Hotel updateHotel(Long hotelId, UpdateHotel updateHotel) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() ->
                        new HotelNotFoundException("Hotel with id: " + hotelId + " does not exist."));

        Hotel updatedHotel = new Hotel(updateHotel, hotel);
        updatedHotel.setId(hotel.getId());

        return hotelRepository.save(updatedHotel);
    }

    @Override
    public String deleteHotel(Long hotelId) {
        if (!hotelRepository.existsById(hotelId)) {
            throw new HotelNotFoundException("Hotel with id: " + hotelId + " does not exist.");
        }

        hotelRepository.deleteById(hotelId);

        return "Hotel with id: " + hotelId + " has been deleted.";
    }

    @Override
    public Hotel findByHotelId(Long hotelId) {
        return hotelRepository.findById(hotelId)
                .orElseThrow(() ->
                        new HotelNotFoundException("Hotel with id: " + hotelId + " does not exist."));
    }

    @Override
    public Hotel saveHotel(Hotel hotel) {
        if (hotel == null) {
            throw new HotelNotFoundException("Hotel object is null.");
        }

        return hotelRepository.save(hotel);
    }
}
