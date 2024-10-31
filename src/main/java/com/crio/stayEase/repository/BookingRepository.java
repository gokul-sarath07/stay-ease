package com.crio.stayEase.repository;

import com.crio.stayEase.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Query("SELECT b FROM Booking b WHERE b.primaryUser.id = ?1")
    List<Booking> findByUserId(Long userId);
}
