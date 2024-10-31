package com.crio.stayEase.repository;

import com.crio.stayEase.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    @Query("SELECT h FROM Hotel h WHERE h.numberOfAvailableRooms > 0")
    List<Hotel> findByAvailableHotel();
}
