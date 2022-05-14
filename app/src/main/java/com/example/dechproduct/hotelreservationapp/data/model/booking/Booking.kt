package com.example.dechproduct.hotelreservationapp.data.model.booking

import android.os.Parcelable
import com.example.dechproduct.hotelreservationapp.data.model.guest.Guest
import com.example.dechproduct.hotelreservationapp.data.model.payment.Payment
import com.example.dechproduct.hotelreservationapp.data.model.guest.Ticket
import com.example.dechproduct.hotelreservationapp.data.model.room.Room
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
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

    var status: BookingStatus?,

    var breakfast: Boolean?,

    var isAddonBed: Boolean?,



    ) : Parcelable {
    fun toBookingDTO(): BookingDTO {
        var dateFormat = SimpleDateFormat("dd-MM-yyyy")
        return BookingDTO(
            bookingID = bookingID,
            guest = guest?.toGuestDTO(),
            ticket = ticket?.toTicketDTO(),
            room = room?.toRoomDTO(),
            payment = payment?.toPaymentDTO(),
            arrivalDate = dateFormat.format(arrivalDate),
            departDate = dateFormat.format(departDate),
            adultCount = adultCount,
            childCount = childCount,
            status = status?.let { BookingStatus.pack(it) },
            breakfast = breakfast,
            isAddonBed = isAddonBed,
        )
    }
}