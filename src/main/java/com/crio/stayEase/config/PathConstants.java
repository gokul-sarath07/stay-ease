package com.crio.stayEase.config;

public class PathConstants {
    //  Base Paths
    public static final String API_BASE_PATH = "/api/v1";
    public static final String USER_BASE_PATH = API_BASE_PATH + "/auth";
    public static final String HOTEL_BASE_PATH = API_BASE_PATH + "/hotels";
    public static final String BOOKING_BASE_PATH = API_BASE_PATH + "/bookings";

    // User Paths
    public static final String REGISTER_USER = USER_BASE_PATH + "/register";
    public static final String LOGIN_USER = USER_BASE_PATH + "/login";

    // Hotel Paths
    public static final String MODIFY_HOTEL = HOTEL_BASE_PATH + "/{hotelId}";

    // Booking Paths
    public static final String ROOM_BOOKING = BOOKING_BASE_PATH + "/hotels/{hotelId}/book";
    public static final String CANCEL_BOOKING = BOOKING_BASE_PATH + "/{bookingId}";
    public static final String USER_BOOKINGS  =  BOOKING_BASE_PATH + "/user/{userId}";
    public static final String GET_BOOKING = BOOKING_BASE_PATH + "/{bookingId}";
}
