package com.example.dechproduct.hotelreservationapp.data.model.employee


import com.example.dechproduct.hotelreservationapp.util.Constants
import com.google.gson.annotations.SerializedName

data class AccessDTO(
    @SerializedName(Constants.API_ACCESS_KEY_ID)
    var accessID: String,
    @SerializedName(Constants.API_ACCESS_KEY_USERNAME)
    var userName: String,
    @SerializedName(Constants.API_ACCESS_KEY_PASSWORD)
    var password: String,
    @SerializedName(Constants.API_ACCESS_KEY_EMPLOYEE)
    var employee: Employee,
) {

    fun toStaff(): Access {
        return Access(
            accessID = accessID,
            userName = userName,
            password = password,
            employee = employee
        )
    }
}