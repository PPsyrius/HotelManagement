package com.example.dechproduct.hotelreservationapp.data.model.payment

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Parcelable
import android.util.Base64
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
        var image: Bitmap? = null
        try {
            val imageBytes = Base64.decode(photo, 0)
            image = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
        }
        catch (e:Exception){
        }

        return Payment(
            paymentID = paymentID,
            type = type?.let { PaymentType.unpack(it) },
            photo = image
        )
    }
}