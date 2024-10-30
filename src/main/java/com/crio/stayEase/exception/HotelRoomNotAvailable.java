package com.crio.stayEase.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class HotelRoomNotAvailable extends RuntimeException {
    public HotelRoomNotAvailable() {
        super();
    }

    public HotelRoomNotAvailable(String message) {
        super(message);
    }
}
