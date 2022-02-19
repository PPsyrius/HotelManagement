package com.example.dechproduct.hotelreservationapp.data.model

import android.os.Parcelable
import com.example.dechproduct.hotelreservationapp.util.Constants
import kotlinx.parcelize.Parcelize

@Parcelize
data class Booking(

    var bookingID: String? = "",

    var firstName: String? = "",

    var lastName: String? = "",

    var phoneNumber: String? = "",

    var address: Address?,

    var verificationID: String? = "",

    var arrivalDate: String? = "",

    var departDate: String? = "",

    var paymentType: String? = "",

    var guestStatus: String? = Constants.GUEST_STATUS_ONCREATE,

    var guestPass: GuestPass?,

    var guestRoom: Room?,

    ) : Parcelable {
/*
    fun toBookingDTO(): BookingDTO{
        return BookingDTO(
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
*/
}