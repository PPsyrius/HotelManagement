package com.example.dechproduct.hotelreservationapp.data.model.guest

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
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
        var image: Bitmap? = null
        try {
            val imageBytes = Base64.decode(verificationPhoto, 0)
            image = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
        }
        catch (e:Exception){
        }
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
            verificationPhoto = image
        )
    }
}
