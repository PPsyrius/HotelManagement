package com.example.dechproduct.hotelreservationapp.data.model.guest

import android.os.Parcelable
import com.example.dechproduct.hotelreservationapp.util.Constants
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TicketDTO(
    @SerializedName(Constants.API_TICKET_KEY_ID)
    var ticketID: String,
    @SerializedName(Constants.API_TICKET_KEY_USERNAME)
    var userName: String,
    @SerializedName(Constants.API_TICKET_KEY_PASSWORD)
    var password: String,
    @SerializedName(Constants.API_TICKET_KEY_GUEST)
    var guest: Guest?,
): Parcelable