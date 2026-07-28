package com.seu.hotel_booking;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GuestService {

    private GuestRepository guestRepository;

    public void saveGuest(Guest guest){

        guestRepository.save(guest);
    }
}
