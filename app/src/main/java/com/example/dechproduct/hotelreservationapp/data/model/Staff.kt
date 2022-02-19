package com.example.dechproduct.hotelreservationapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Staff(

    var userID: String?,

    var userName: String?,

    var displayName: String?,

    var password: String = "",

    var userType: String = "",

    ) : Parcelable {
}