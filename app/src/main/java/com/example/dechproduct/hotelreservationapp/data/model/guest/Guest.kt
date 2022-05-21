package com.example.dechproduct.hotelreservationapp.data.model.guest

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

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

    var verificationPhoto: String? = "",
) : Parcelable {
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
            verificationPhoto = verificationPhoto
        )
    }
}
