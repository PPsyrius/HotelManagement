package com.example.dechproduct.hotelreservationapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class RoomStatus(
    var displayName: String?,

    var internalCode: String?,

    ) : Parcelable {
    SALE("Ready", "RS"),
    CLEAN("Not Cleaned", "NC"),
    ORDER("Out of Order", "NF"),
    OCUPD("Occupied", "U0"),
    INSPC("Inspecting", "WI"),
}