package com.example.dechproduct.hotelreservationapp.data.model.utility.room

import android.os.Parcelable
import com.example.dechproduct.hotelreservationapp.data.model.utility.booking.GuestStatus
import kotlinx.parcelize.Parcelize

@Parcelize
enum class RoomType(
    var displayName: String,

    var internalCode: String,

    ) : Parcelable {
    STANDARD("Standard", "SD"),
    SUPERIOR("Superior","SP"),
    DELUXE("Deluxe", "DX"),
    SUITE("Suite", "SE");

    companion object {
        private fun getByInternalCode(key: String) = RoomType.values().find { it.internalCode == key }
        fun pack(roomType: RoomType): String = roomType.internalCode
        fun unpack(roomType: String): RoomType? = RoomType.getByInternalCode(roomType)
    }
}