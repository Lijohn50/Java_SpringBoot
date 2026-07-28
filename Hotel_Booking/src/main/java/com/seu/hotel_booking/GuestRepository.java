package com.seu.hotel_booking;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest, Integer> {
}
