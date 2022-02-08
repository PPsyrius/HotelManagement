package com.example.dechproduct.hotelreservationapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RoomStatus(

    var displayName: String?,

    var internalCode: String?,

    ) : Parcelable {
}