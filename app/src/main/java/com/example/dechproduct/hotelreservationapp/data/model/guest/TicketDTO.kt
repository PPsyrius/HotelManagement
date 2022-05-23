package com.example.dechproduct.hotelreservationapp.data.model.guest

import android.os.Parcelable
import com.example.dechproduct.hotelreservationapp.data.model.room.*
import com.example.dechproduct.hotelreservationapp.util.Constants
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


data class TicketDTO(
    @SerializedName(Constants.API_TICKET_KEY_ID)
    var ticketID: String?,
    @SerializedName(Constants.API_TICKET_KEY_USERNAME)
    var userName: String?,
    @SerializedName(Constants.API_TICKET_KEY_PASSWORD)
    var password: String?,
//    @Expose(serialize = false, deserialize = false)
    @Transient
    @SerializedName(Constants.API_TICKET_KEY_GUEST)
    var guest: GuestDTO?,
){
    fun toTicket(): Ticket {
        return Ticket(
            ticketID = ticketID,
            userName = userName,
            password = password,
            guest = guest?.toGuest()
        )
    }
}