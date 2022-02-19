package com.example.dechproduct.hotelreservationapp.util

object Constants {
    //Guest Model
    const val GUEST_STATUS_ONCREATE = "created"

    //Room Model
    const val ROOM_TYP_STANDARD = "standard"
    const val ROOM_TYP_DELUXE = "deluxe"
    const val ROOM_TYP_SUITE = "suite"

    const val ROOM_BED_SINGLE = "single"
    const val ROOM_BED_DOUBLE = "twin"

    const val ROOM_LOC_NORMAL = "sandwich"
    const val ROOM_LOC_CORNER = "corner"

    const val ROOM_MAX_CAPACITY = 2

    //Database via API
    const val API_USER_BASE_URL =
        "http://10.0.2.2:3000/"
    const val API_USER_INDEX_URL =
        "user"
    const val API_USER_KEY_PASSWORD = "password"
    const val API_USER_KEY_ID = "userID"
    const val API_USER_KEY_NAME = "displayName"
    const val API_USER_KEY_USERNAME = "userName"
    const val API_USER_KEY_TYPE = "userType"

    const val API_BOOK_BASE_URL =
        "http://10.0.2.2:3001/"
    const val API_BOOK_INDEX_URL =
        "booking"
    const val API_BOOK_KEY_FNAME = "firstName"
    const val API_BOOK_KEY_LNAME = "lastName"
    const val API_BOOK_KEY_ID = "bookingID"
    const val API_BOOK_KEY_PHONE = "phoneNumber"
    const val API_BOOK_KEY_PAYMENT = "paymentType"
    const val API_BOOK_KEY_DATE_IN = "arrivalDate"
    const val API_BOOK_KEY_DATE_OUT = "departDate"
    const val API_BOOK_KEY_VERID = "verificationID"
    const val API_BOOK_KEY_ADDRESS = "address"
    const val API_BOOK_KEY_STATUS = "guestStatus"
    const val API_BOOK_KEY_PASS = "guestPass"
    const val API_BOOK_KEY_ROOM = "guestRoom"

    //Persistent Data
    const val SHARED_PREF_NAME = "USER_SESSION"

    //Miscellaneous
    const val LOGGED_IN_USER_NAME = "LOGGED_IN_USER"
    const val LOGGED_IN_DISPLAY_NAME = "LOGGED_IN_USER_NAME"
}