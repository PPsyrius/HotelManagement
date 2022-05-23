package com.example.dechproduct.hotelreservationapp.data.model.employee

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import android.os.Parcelable
import com.example.dechproduct.hotelreservationapp.util.Constants
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Access(

    var accessID: String,

    var userName: String,

    var password: String,

    var employee: Employee,

    ) : Parcelable {

    fun toAccessDTO(): AccessDTO {
        return AccessDTO(
            accessID = accessID,
            userName = userName,
            password = password,
            employee = employee.toEmployeeDTO()
        )
    }
}