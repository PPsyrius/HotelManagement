package com.example.dechproduct.hotelreservationapp.data.model.utility.room

import android.os.Parcelable
import com.example.dechproduct.hotelreservationapp.data.model.utility.booking.GuestStatus
import kotlinx.parcelize.Parcelize

@Parcelize
enum class BedType(
    var displayName: String,

    var internalCode: String,

    ) : Parcelable {
    SINGLE("Single Bed", "SB"),
    TWIN("Twin Beds", "DB");

    companion object {
        fun getByInternalCode(key: String) = BedType.values().find { it.internalCode == key }
    }
}