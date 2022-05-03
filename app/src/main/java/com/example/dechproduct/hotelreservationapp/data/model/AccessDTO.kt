package com.example.dechproduct.hotelreservationapp.data.model


import com.example.dechproduct.hotelreservationapp.util.Constants
import com.google.gson.annotations.SerializedName

data class AccessDTO(
    @SerializedName(Constants.API_USER_KEY_ID)
    var userID: String?,
    @SerializedName(Constants.API_USER_KEY_USERNAME)
    var userName: String?,
    @SerializedName(Constants.API_USER_KEY_NAME)
    var displayName: String?,
    @SerializedName(Constants.API_USER_KEY_PASSWORD)
    var password: String,
    @SerializedName(Constants.API_USER_KEY_TYPE)
    var userType: String,
) {

    fun toStaff(): Access{
        return Access(
            userID = userID,
            userName = userName,
            displayName = displayName,
            password = password,
            userType = userType,
        )
    }
}