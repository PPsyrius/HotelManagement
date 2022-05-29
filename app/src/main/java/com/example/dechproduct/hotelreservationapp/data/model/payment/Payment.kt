package com.example.dechproduct.hotelreservationapp.data.model.payment

import android.graphics.Bitmap
import android.os.Parcelable
import android.util.Base64
import com.example.dechproduct.hotelreservationapp.data.model.guest.Ticket
import com.example.dechproduct.hotelreservationapp.util.Constants
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.io.ByteArrayOutputStream

@Parcelize
data class Payment(

    var paymentID: String? = "",

    var type: PaymentType? = PaymentType.None,

    var photo: Bitmap? = null,
) : Parcelable{
    private fun encodeImage(photo: Bitmap): String? {
        val bOutput = ByteArrayOutputStream()
        photo.compress(Bitmap.CompressFormat.JPEG, 100, bOutput)
        return Base64.encodeToString(bOutput.toByteArray(), Base64.NO_WRAP)
    }

    fun toPaymentDTO(): PaymentDTO {
        return PaymentDTO(
            paymentID = paymentID,
            type = type?.let { PaymentType.pack(it) },
            photo = photo?.let { encodeImage(it) }
        )
    }
}