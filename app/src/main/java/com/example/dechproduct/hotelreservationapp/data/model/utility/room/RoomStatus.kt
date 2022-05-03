package com.example.dechproduct.hotelreservationapp.data.model.utility.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import android.os.Parcelable
import com.example.dechproduct.hotelreservationapp.data.model.utility.booking.GuestStatus
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
enum class RoomStatus(
    var displayName: String,

    var internalCode: String,

    ) : Parcelable {
    READY("Ready", "RA"),
    RESERVED("Reserved", "RS"),
    GUARANTEED("Guaranteed Room", "GR"),
    REQ_CLEAN("Request Cleaning", "NC"),
    OUTORDER("Out of Order", "NF"),
    OCCUPIED("Occupied", "U0"),
    INSPECTING("Inspecting", "WI"),
    SOF_CLEAN("Cleaning for Extensions", "UC"),
    NONE("Default", "NON");

    companion object {
        private fun getByInternalCode(key: String) = RoomStatus.values().find { it.internalCode == key }
        fun pack(roomStatus: RoomStatus): String = roomStatus.internalCode
        fun unpack(roomStatus: String): RoomStatus? = getByInternalCode(roomStatus)
    }
}