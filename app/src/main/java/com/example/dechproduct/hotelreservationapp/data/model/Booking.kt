package com.example.dechproduct.hotelreservationapp.data.model

import android.os.Build
import android.os.Parcelable
import com.example.dechproduct.hotelreservationapp.data.model.utility.booking.Guest
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

    var bookingID: String?,

    var guest: Guest?,

    var ticket: Ticket?,

    var room: Room?,

    var payment: Payment?,

    var arrivalDate: Date?,

    var departDate: Date?,

    var adultCount: Int?,

    var childCount: Int?,

    var bookingStatus: String?,

    var breakfast: Boolean?,

    var isAddonBed: Boolean?,

    ) : Parcelable {
    fun toBookingDTO(): BookingDTO {
        var dateFormat = SimpleDateFormat("dd-MM-yyyy")
        return BookingDTO(
            bookingID = bookingID,
            guest = guest
            verificationID = verificationID?.id,
            arrivalDate = dateFormat.format(arrivalDate),
            departDate = dateFormat.format(departDate),
            adultCount = adultCount,
            childCount = childCount,
            paymentType = paymentType?.let{PaymentType.pack(it)},
            guestStatus = guestStatus?.let{GuestStatus.pack(it)},
            guestPass = guestPass,
            guestRoom = guestRoom?.toRoomDTO(),
            breakfast = breakfast,
            isAddonBed = isAddonBed,
        )
    }
}