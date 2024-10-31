package com.crio.stayEase.controller;

import com.crio.stayEase.dto.CreateHotel;
import com.crio.stayEase.dto.UpdateHotel;
import com.crio.stayEase.entity.Hotel;
import com.crio.stayEase.service.HotelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.crio.stayEase.config.PathConstants.*;

@RestController
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping(HOTEL_BASE_PATH)
    public ResponseEntity<Hotel> createHotel(@Valid @RequestBody CreateHotel createHotel) {
        Hotel hotel = hotelService.createHotel(createHotel);

        return ResponseEntity.ok(hotel);
    }

    @GetMapping(HOTEL_BASE_PATH)
    public ResponseEntity<List<Hotel>> getAllHotel() {
        List<Hotel> hotels = hotelService.getAllHotels();

        return ResponseEntity.ok(hotels);
    }

    @PutMapping(MODIFY_HOTEL)
    public ResponseEntity<Hotel> updateHotel(@PathVariable("hotelId") Long hotelId, @Valid @RequestBody UpdateHotel updateHotel) {
        Hotel hotel = hotelService.updateHotel(hotelId, updateHotel);

        return ResponseEntity.ok(hotel);
    }

    @DeleteMapping(MODIFY_HOTEL)
    public ResponseEntity<String> deleteHotel(@PathVariable("hotelId") Long hotelId) {
        String status = hotelService.deleteHotel(hotelId);

        return ResponseEntity.ok(status);
    }
}
