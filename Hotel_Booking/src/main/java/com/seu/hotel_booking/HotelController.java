package com.seu.hotel_booking;


import jakarta.persistence.GeneratedValue;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hotel")
@RequiredArgsConstructor
public class HotelController {

    private GuestService guestService;

    @GetMapping("/form")
    public String showBookingForm(){

        return "form";
    }
}
