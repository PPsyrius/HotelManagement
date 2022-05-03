package com.example.dechproduct.hotelreservationapp.data.model


import com.example.dechproduct.hotelreservationapp.data.model.utility.booking.GuestStatus
import com.example.dechproduct.hotelreservationapp.data.model.utility.booking.PaymentType
import com.example.dechproduct.hotelreservationapp.data.model.utility.booking.VerificationID
import com.example.dechproduct.hotelreservationapp.util.Constants
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*

data class BookingDTO(
    @SerializedName(Constants.API_BOOK_KEY_ID)
    var bookingID: String?,
    @SerializedName(Constants.API_BOOK_KEY_FNAME)
    var guest: Guest?,
    @SerializedName(Constants.API_BOOK_KEY_LNAME)
    var ticket: Ticket?,
    @SerializedName(Constants.API_BOOK_KEY_PHONE)
    var room: Room?,
    @SerializedName(Constants.API_BOOK_KEY_ADDRESS)
    var payment: Payment?,
    @SerializedName(Constants.API_BOOK_KEY_DATE_IN)
    var arrivalDate: String?,
    @SerializedName(Constants.API_BOOK_KEY_DATE_OUT)
    var departDate: String?,
    @SerializedName(Constants.API_BOOK_KEY_ADULT)
    var adultCount: Int?,
    @SerializedName(Constants.API_BOOK_KEY_CHILD)
    var childCount: Int?,
    @SerializedName(Constants.API_BOOK_KEY_PAYMENT)
    var bookingStatus: String?,
    @SerializedName(Constants.API_BOOK_KEY_STATUS)
    var breakfast: Boolean?,
    @SerializedName(Constants.API_BOOK_KEY_ADDONBED)
    var isAddonBed: Boolean?,
) {
    fun toBooking(): Booking{
        var dateFormat = SimpleDateFormat("dd-MM-yyyy")
        return Booking(
            bookingID = bookingID,
            firstName = firstName,
            lastName = lastName,
            phoneNumber = phoneNumber,
            address = address,
            verificationID = verificationID?.let { VerificationID(it) },
            arrivalDate = dateFormat.parse(arrivalDate),
            departDate = dateFormat.parse(departDate),
            adultCount = adultCount,
            childCount = childCount,
            paymentType = paymentType?.let { PaymentType.unpack(it) },
            guestStatus = guestStatus?.let { GuestStatus.unpack(it) },
            guestPass = Ticket(),
            guestRoom = Guest,
            breakfast = breakfast,
            isAddonBed = isAddonBed,
        )
    }
}