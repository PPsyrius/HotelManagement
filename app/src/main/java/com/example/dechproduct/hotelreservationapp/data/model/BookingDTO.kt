package com.example.dechproduct.hotelreservationapp.data.model


import android.os.Build
import com.example.dechproduct.hotelreservationapp.data.model.utility.booking.GuestStatus
import com.example.dechproduct.hotelreservationapp.data.model.utility.booking.PaymentType
import com.example.dechproduct.hotelreservationapp.data.model.utility.booking.VerificationID
import com.example.dechproduct.hotelreservationapp.util.Constants
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

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
    @SerializedName(Constants.API_BOOK_KEY_ADULT)
    var adultCount: Int?,
    @SerializedName(Constants.API_BOOK_KEY_CHILD)
    var childCount: Int?,
    @SerializedName(Constants.API_BOOK_KEY_PAYMENT)
    var paymentType: String?,
    @SerializedName(Constants.API_BOOK_KEY_STATUS)
    var guestStatus: String?,
    @SerializedName(Constants.API_BOOK_KEY_PASS)
    var guestPass: GuestPass?,
    @SerializedName(Constants.API_BOOK_KEY_ROOM)
    var guestRoom: RoomDTO?,
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
            guestPass = guestPass,
            guestRoom = guestRoom?.toRoom(),
        )
    }
}