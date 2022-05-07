package com.example.dechproduct.hotelreservationapp.data.model.payment

import android.os.Parcelable
import com.example.dechproduct.hotelreservationapp.data.model.guest.Ticket
import com.example.dechproduct.hotelreservationapp.util.Constants
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PaymentDTO(
    @SerializedName(Constants.API_PAYMENT_KEY_ID)
    var paymentID: String?,
    @SerializedName(Constants.API_PAYMENT_KEY_TYPE)
    var type: String?,
    @SerializedName(Constants.API_PAYMENT_KEY_PHOTO)
    var photo: String?,
) : Parcelable {
    fun toPayment(): Payment {
        return Payment(
            paymentID = paymentID,
            type = type?.let { PaymentType.unpack(it) },
            photo = photo
        )
    }
}