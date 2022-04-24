package com.example.dechproduct.hotelreservationapp.util

object Constants {
    //TODO: All model enum?
    //Guest Model
    const val GUEST_STATUS_CREATED = "CREATED"
    const val GUEST_STATUS_RESERVED = "RESERVED"
    const val GUEST_STATUS_CHECKIN = "CHECKIN"
    const val GUEST_STATUS_CHECKOUT = "CHECKOUT"
    const val GUEST_STATUS_ERROR = "ERR"

    //Room Model
    const val ROOM_TYP_STANDARD = "Standard"
    const val ROOM_TYP_DELUXE = "Deluxe"
    const val ROOM_TYP_SUITE = "Suite"

    const val ROOM_BED_SINGLE = "Single"
    const val ROOM_BED_DOUBLE = "Twin"

    const val ROOM_DEFAULT_CAPACITY = 2

    //Database via API
    const val API_USER_BASE_URL =
        "http://10.0.2.2:3000/"
    const val API_USER_INDEX_URL =
        "user/"
    const val API_USER_KEY_PASSWORD = "password"
    const val API_USER_KEY_ID = "userID"
    const val API_USER_KEY_NAME = "displayName"
    const val API_USER_KEY_USERNAME = "userName"
    const val API_USER_KEY_TYPE = "userType"

    const val API_BOOK_BASE_URL =
        "http://10.0.2.2:3001/"
    const val API_BOOK_INDEX_URL =
        "booking/"
    const val API_BOOK_KEY_FNAME = "firstName"
    const val API_BOOK_KEY_LNAME = "lastName"
    const val API_BOOK_KEY_ID = "bookingID"
    const val API_BOOK_KEY_PHONE = "phoneNumber"
    const val API_BOOK_KEY_PAYMENT = "paymentType"
    const val API_BOOK_KEY_DATE_IN = "arrivalDate"
    const val API_BOOK_KEY_DATE_OUT = "departDate"
    const val API_BOOK_KEY_ADULT = "adult"
    const val API_BOOK_KEY_CHILD = "child"
    const val API_BOOK_KEY_VERID = "verificationID"
    const val API_BOOK_KEY_ADDRESS = "address"
    const val API_BOOK_KEY_STATUS = "guestStatus"
    const val API_BOOK_KEY_PASS = "guestPass"
    const val API_BOOK_KEY_ROOM = "guestRoom"

    const val API_ROOM_BASE_URL =
        "http://10.0.2.2:3002/"
    const val API_ROOM_INDEX_URL =
        "room/"
    const val API_ROOM_KEY_ID = "roomID"
    const val API_ROOM_KEY_TYPE = "roomType"
    const val API_ROOM_KEY_BEDS = "roomBeds"
    const val API_ROOM_KEY_CAP = "maxCap"
    const val API_ROOM_KEY_FLOOR = "posFloor"
    const val API_ROOM_KEY_FEATURES = "features"
    const val API_ROOM_ADDON_FEATURES = "addon"
    const val API_ROOM_BREAKFAST_FEATURES = "breakfast"
    const val API_ROOM_KEY_SMOKING = "smoking"
    const val API_ROOM_KEY_STAT = "roomStatus"
    
    const val API_ROOM_KEY_WALK = "isWalking"
    const val API_ROOM_KEY_DEVICES = "deviceList"
    const val API_ROOM_KEY_PRICE = "roomPrice"

    const val API_INFO_BASE_URL =
        "http://10.0.2.2:3003/"
    const val API_INFO_INDEX_URL =
        "info/"

    //Shared Preference
    const val SHARED_PREF_NAME = "USER_SESSION"
    const val LOGGED_IN_USER_NAME = "LOGGED_IN_USER"
    const val LOGGED_IN_DISPLAY_NAME = "LOGGED_IN_USER_NAME"
    const val RESERVED_ID = "RESERVED_ID"
}