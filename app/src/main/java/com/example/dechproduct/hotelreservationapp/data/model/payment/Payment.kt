package com.example.dechproduct.hotelreservationapp.data.model.payment

import android.os.Parcelable
import com.example.dechproduct.hotelreservationapp.data.model.guest.Ticket
import com.example.dechproduct.hotelreservationapp.util.Constants
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Payment(

    var paymentID: String? = "",

    var type: PaymentType? = PaymentType.None,

    var photo: String? = "",
) : Parcelable {
    fun toPaymentDTO(): PaymentDTO {
        return PaymentDTO(
            paymentID = paymentID,
            type = type?.let { PaymentType.pack(it) },
            photo = photo
        )
    }
}