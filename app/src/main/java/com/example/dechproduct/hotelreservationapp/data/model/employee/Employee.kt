package com.example.dechproduct.hotelreservationapp.data.model.employee

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Employee(

    var employeeID: String?,

    var firstName: String?,

    var lastName: String?,

    var department: String?,

    var position: String?,

) : Parcelable {
    fun toGuestDTO(): EmployeeDTO {
        return EmployeeDTO(
            employeeID = employeeID,
            firstName = firstName,
            lastName = lastName,
            department = department,
            position = position
        )
    }
}
