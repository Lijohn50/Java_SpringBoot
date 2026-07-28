package com.seu.hotel_booking;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Embeddable
public class PassportInfo {

    private int passportNum;
    private String expiryDate;
    private String Country;
}
