package com.example.dechproduct.hotelreservationapp.data.model


import com.example.dechproduct.hotelreservationapp.util.Constants
import com.google.gson.annotations.SerializedName

data class BookingDTO(
    @SerializedName(Constants.API_BOOK_KEY_ID)
    var bookingID: String?,
    @SerializedName(Constants.API_BOOK_KEY_FNAME)
    var firstName: String?,
    @SerializedName(Constants.API_BOOK_KEY_LNAME)
    var lastName: String?,
    @SerializedName(Constants.API_BOOK_KEY_PHONE)
    var phoneNumber: String?,
    @SerializedName(Constants.API_BOOK_KEY_ADDRESS)
    var address: Address?,
    @SerializedName(Constants.API_BOOK_KEY_VERID)
    var verificationID: String?,
    @SerializedName(Constants.API_BOOK_KEY_DATE_IN)
    var arrivalDate: String?,
    @SerializedName(Constants.API_BOOK_KEY_DATE_OUT)
    var departDate: String?,
    @SerializedName(Constants.API_BOOK_KEY_PAYMENT)
    var paymentType: String?,
    @SerializedName(Constants.API_BOOK_KEY_STATUS)
    var guestStatus: String?,
    @SerializedName(Constants.API_BOOK_KEY_PASS)
    var guestPass: GuestPass?,
    @SerializedName(Constants.API_BOOK_KEY_ROOM)
    var guestRoom: Room?,
) {

    fun toBooking(): Booking{
        return Booking(
            bookingID = bookingID,
            firstName = firstName,
            lastName = lastName,
            phoneNumber = phoneNumber,
            address = address,
            verificationID = verificationID,
            arrivalDate = arrivalDate,
            departDate = departDate,
            paymentType = paymentType,
            guestStatus = guestStatus,
            guestPass = guestPass,
            guestRoom = guestRoom,
        )
    }
}