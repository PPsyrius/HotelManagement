package com.example.dechproduct.hotelreservationapp.data.model.guest

import com.example.dechproduct.hotelreservationapp.util.Constants
import com.google.gson.annotations.SerializedName

data class GuestDTO(
    @SerializedName(Constants.API_GUEST_KEY_ID)
    var guestID: String?,
    @SerializedName(Constants.API_GUEST_KEY_FNAME)
    var firstName: String?,
    @SerializedName(Constants.API_GUEST_KEY_LNAME)
    var lastName: String?,
    @SerializedName(Constants.API_GUEST_KEY_PHONE)
    var phoneNumber: String?,
    @SerializedName(Constants.API_GUEST_KEY_ADDRESS)
    var address: List<String>?,
    @SerializedName(Constants.API_GUEST_KEY_REGION)
    var region: String?,
    @SerializedName(Constants.API_GUEST_KEY_POSTAL)
    var postalCode: String?,
    @SerializedName(Constants.API_GUEST_KEY_COUNTRY)
    var country: String?,
    @SerializedName(Constants.API_GUEST_KEY_VERID)
    var verificationID: String?,
    @SerializedName(Constants.API_GUEST_KEY_VERPHOTO)
    var verificationPhoto: String?,
) {
    fun toGuest(): Guest {
        return Guest(
            guestID = guestID,
            firstName = firstName,
            lastName = lastName,
            phoneNumber = phoneNumber,
            address = address,
            region = region,
            postalCode = postalCode,
            country = country,
            verificationID = verificationID?.let { VerificationID(it) },
            verificationPhoto = verificationPhoto
        )
    }
}
