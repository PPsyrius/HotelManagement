package com.example.dechproduct.hotelreservationapp.data.model.employee

import com.example.dechproduct.hotelreservationapp.util.Constants
import com.google.gson.annotations.SerializedName

data class EmployeeDTO(
    @SerializedName(Constants.API_ACCESS_KEY_EID)
    var employeeID: String?,
    @SerializedName(Constants.API_ACCESS_KEY_FNAME)
    var firstName: String?,
    @SerializedName(Constants.API_ACCESS_KEY_LNAME)
    var lastName: String?,
    @SerializedName(Constants.API_ACCESS_KEY_DEP)
    var department: String?,
    @SerializedName(Constants.API_ACCESS_KEY_POS)
    var position: String?,
) {
    fun toEmployee(): Employee {
        return Employee(
            employeeID = employeeID,
            firstName = firstName,
            lastName = lastName,
            department = department,
            position = position
        )
    }
}
