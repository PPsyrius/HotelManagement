package com.example.dechproduct.hotelreservationapp.data.model.booking


import com.example.dechproduct.hotelreservationapp.data.model.guest.Guest
import com.example.dechproduct.hotelreservationapp.data.model.payment.Payment
import com.example.dechproduct.hotelreservationapp.data.model.guest.Ticket
import com.example.dechproduct.hotelreservationapp.data.model.room.Room
import com.example.dechproduct.hotelreservationapp.util.Constants
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat

data class BookingDTO(
    @SerializedName(Constants.API_BOOKING_KEY_ID)
    var bookingID: String,
    @SerializedName(Constants.API_BOOKING_KEY_GUEST)
    var guest: Guest?,
    @SerializedName(Constants.API_BOOKING_KEY_TICKET)
    var ticket: Ticket?,
    @SerializedName(Constants.API_BOOKING_KEY_ROOM)
    var room: Room?,
    @SerializedName(Constants.API_BOOKING_KEY_PAYMENT)
    var payment: Payment?,
    @SerializedName(Constants.API_BOOKING_KEY_DATE_IN)
    var arrivalDate: String?,
    @SerializedName(Constants.API_BOOKING_KEY_DATE_OUT)
    var departDate: String?,
    @SerializedName(Constants.API_BOOKING_KEY_ADULT)
    var adultCount: Int?,
    @SerializedName(Constants.API_BOOKING_KEY_CHILD)
    var childCount: Int?,
    @SerializedName(Constants.API_BOOKING_KEY_STATUS)
    var bookingStatus: String?,
    @SerializedName(Constants.API_BOOKING_KEY_BREAKFAST)
    var breakfast: Boolean?,
    @SerializedName(Constants.API_BOOKING_KEY_ADDBED)
    var isAddonBed: Boolean?,
) {
    fun toBooking(): Booking {
        var dateFormat = SimpleDateFormat("dd-MM-yyyy")
        return Booking(
            bookingID = bookingID,
            guest = guest,
            ticket = ticket,
            room = room,
            payment = payment,
            arrivalDate = dateFormat.parse(arrivalDate),
            departDate = dateFormat.parse(departDate),
            adultCount = adultCount,
            childCount = childCount,
//            paymentType = paymentType?.let { PaymentType.unpack(it) },
//            guestStatus = guestStatus?.let { GuestStatus.unpack(it) },
            bookingStatus = bookingStatus,
            breakfast = breakfast,
            isAddonBed = isAddonBed,
        )
    }
}