package com.example.dechproduct.hotelreservationapp.data.model.room

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class BedType(
    var displayName: String,

    var internalCode: String,

    ) : Parcelable {
    SINGLE("Single Bed", "SB"),
    TWIN("Twin Beds", "DB"),
    NONE("Empty", "NON");

    companion object {
        private fun getByInternalCode(key: String) = BedType.values().find { it.internalCode == key }
        fun pack(bedType: BedType): String = bedType.internalCode
        fun unpack(bedType: String): BedType? = BedType.getByInternalCode(bedType)
    }
}