package com.example.dechproduct.hotelreservationapp.data.model

import android.os.Build
import android.os.Parcelable
import com.example.dechproduct.hotelreservationapp.data.model.utility.booking.GuestStatus
import com.example.dechproduct.hotelreservationapp.data.model.utility.booking.PaymentType
import com.example.dechproduct.hotelreservationapp.data.model.utility.booking.VerificationID
import com.example.dechproduct.hotelreservationapp.util.Constants
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

@Parcelize
data class Booking(

    var bookingID: String? = "",

    var firstName: String? = "",

    var lastName: String? = "",

    var phoneNumber: String? = "",

    var address: Address?,

    var verificationID: VerificationID?,

    var arrivalDate: Date?,
    //TODO:New Date class extension, override toString parseDate error prevention
    var departDate: Date?,

    var adultCount: Int?,

    var childCount: Int?,

    var paymentType: PaymentType?,

    var guestStatus: GuestStatus?,

    var guestPass: GuestPass?,

    var guestRoom: Room?,

    ) : Parcelable {
    fun toBookingDTO(): BookingDTO {
        var dateFormat = SimpleDateFormat("dd-MM-yyyy")
        return BookingDTO(
            bookingID = bookingID,
            firstName = firstName,
            lastName = lastName,
            phoneNumber = phoneNumber,
            address = address,
            verificationID = verificationID?.id,
            arrivalDate = dateFormat.format(arrivalDate),
            departDate = dateFormat.format(departDate),
            adultCount = adultCount,
            childCount = childCount,
            paymentType = paymentType?.let{PaymentType.pack(it)},
            guestStatus = guestStatus?.let{GuestStatus.pack(it)},
            guestPass = guestPass,
            guestRoom = guestRoom?.toRoomDTO(),
        )
    }
}