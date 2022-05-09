package com.example.dechproduct.hotelreservationapp.data.model.room

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class OccupancyStatus(
    var displayName: String,

    var internalCode: String,

    ) : Parcelable {
    TALLY("Reserved", "RS"),
    LOCKED("Guaranteed Room", "GR"),
    NONE("Default", "NON");

    companion object {
        private fun getByInternalCode(key: String) = OccupancyStatus.values().find { it.internalCode == key }
        fun pack(roomStatus: OccupancyStatus): String = roomStatus.internalCode
        fun unpack(roomStatus: String): OccupancyStatus? = getByInternalCode(roomStatus)
    }
}