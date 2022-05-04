package com.example.dechproduct.hotelreservationapp.data.model.employee

import com.example.dechproduct.hotelreservationapp.util.Constants
import com.google.gson.annotations.SerializedName

data class EmployeeDTO(
    @SerializedName(Constants.API_GUEST_KEY_ID)
    var employeeID: String,
    @SerializedName(Constants.API_GUEST_KEY_ID)
    var firstName: String,
    @SerializedName(Constants.API_GUEST_KEY_ID)
    var lastName: String,
    @SerializedName(Constants.API_GUEST_KEY_ID)
    var department: String?,
    @SerializedName(Constants.API_GUEST_KEY_ID)
    var position: String?,
) {
    fun toGuest(): Employee {
        return Employee(
            employeeID = employeeID,
            firstName = firstName,
            lastName = lastName,
            department = department,
            position = position
        )
    }
}
