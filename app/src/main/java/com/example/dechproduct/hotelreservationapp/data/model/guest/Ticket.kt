package com.example.dechproduct.hotelreservationapp.data.model.guest

import android.os.Parcelable
import com.example.dechproduct.hotelreservationapp.util.Constants
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Ticket(

    var ticketID: String? = "",

    var userName: String? = "",

    var password: String? = "",

    var guest: Guest? = Guest(),
) : Parcelable {
    fun toTicketDTO(): TicketDTO {
        return TicketDTO(
            ticketID = ticketID,
            userName = userName,
            password = password,
            guest = guest
        )
    }
}