package com.example.dechproduct.hotelreservationapp.data.model.booking


import com.example.dechproduct.hotelreservationapp.data.model.guest.Guest
import com.example.dechproduct.hotelreservationapp.data.model.guest.GuestDTO
import com.example.dechproduct.hotelreservationapp.data.model.payment.Payment
import com.example.dechproduct.hotelreservationapp.data.model.guest.Ticket
import com.example.dechproduct.hotelreservationapp.data.model.guest.TicketDTO
import com.example.dechproduct.hotelreservationapp.data.model.payment.PaymentDTO
import com.example.dechproduct.hotelreservationapp.data.model.room.Room
import com.example.dechproduct.hotelreservationapp.data.model.room.RoomDTO
import com.example.dechproduct.hotelreservationapp.util.Constants
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat

data class BookingDTO(
    @SerializedName(Constants.API_BOOKING_KEY_ID)
    var bookingID: String?,
    @SerializedName(Constants.API_BOOKING_KEY_GUEST)
    var guest: GuestDTO?,
    @SerializedName(Constants.API_BOOKING_KEY_TICKET)
    var ticket: TicketDTO?,
    @SerializedName(Constants.API_BOOKING_KEY_ROOM)
    var room: RoomDTO?,
    @SerializedName(Constants.API_BOOKING_KEY_PAYMENT)
    var payment: PaymentDTO?,
    @SerializedName(Constants.API_BOOKING_KEY_DATE_IN)
    var arrivalDate: String?,
    @SerializedName(Constants.API_BOOKING_KEY_DATE_OUT)
    var departDate: String?,
    @SerializedName(Constants.API_BOOKING_KEY_ADULT)
    var adultCount: Int?,
    @SerializedName(Constants.API_BOOKING_KEY_CHILD)
    var childCount: Int?,
    @SerializedName(Constants.API_BOOKING_KEY_STATUS)
    var status: String?,
    @SerializedName(Constants.API_BOOKING_KEY_BREAKFAST)
    var breakfast: Boolean?,
    @SerializedName(Constants.API_BOOKING_KEY_ADDBED)
    var isAddonBed: Boolean?,
) {
    fun toBooking(): Booking {
        var dateFormat = SimpleDateFormat("dd-MM-yyyy")
        return Booking(
            bookingID = bookingID,
            guest = guest?.toGuest(),
            ticket = ticket?.toTicket(),
            room = room?.toRoom(),
            payment = payment?.toPayment(),
            arrivalDate = dateFormat.parse(arrivalDate),
            departDate = dateFormat.parse(departDate),
            adultCount = adultCount,
            childCount = childCount,
            status = status?.let { BookingStatus.unpack(it) },
            breakfast = breakfast,
            isAddonBed = isAddonBed,
        )
    }
}