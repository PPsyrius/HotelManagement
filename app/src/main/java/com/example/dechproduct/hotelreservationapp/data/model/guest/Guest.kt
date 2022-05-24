package com.example.dechproduct.hotelreservationapp.data.model.guest

import android.graphics.Bitmap
import android.os.Parcelable
import android.util.Base64
import kotlinx.parcelize.Parcelize
import java.io.ByteArrayOutputStream

@Parcelize
data class Guest(

    var guestID: String? = "",

    var firstName: String? = "",

    var lastName: String? = "",

    var phoneNumber: String? = "",

    var address: List<String>? = listOf<String>(),

    var region: String? = "",

    var postalCode: String?= "",

    var country: String? = "",

    var verificationID: VerificationID? = VerificationID(),

    var verificationPhoto: Bitmap? = null,
) : Parcelable {
    private fun encodeImage(photo: Bitmap): String? {
        val bOutput = ByteArrayOutputStream()
        photo.compress(Bitmap.CompressFormat.JPEG, 100, bOutput)
        return Base64.encodeToString(bOutput.toByteArray(), Base64.NO_WRAP)
    }

    fun toGuestDTO(): GuestDTO {
        return GuestDTO(
            guestID = guestID,
            firstName = firstName,
            lastName = lastName,
            phoneNumber = phoneNumber,
            address = address,
            region = region,
            postalCode = postalCode,
            country = country,
            verificationID = verificationID.toString(),
            verificationPhoto = verificationPhoto?.let { encodeImage(it) }
        )
    }
}
