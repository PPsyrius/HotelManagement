package com.example.dechproduct.hotelreservationapp.data.model.utility.booking

import android.os.Parcelable
import com.example.dechproduct.hotelreservationapp.data.model.utility.room.RoomStatus
import kotlinx.parcelize.Parcelize

@Parcelize
enum class GuestStatus(
    var displayName: String,

    var internalCode: String,
): Parcelable{
    CREATED("Reservation Created","CRE"),
    RESERVED("Reservation Completed","RSC"),
    CHECK_IN("Check-In Completed","CHI"),
    CHECK_OUT("Check-Out Completed","CHO"),
    CANCEL("Guest Cancelled", "CAX"),
    ERROR("Error State","ERR"),
    NONE("Default","NON");

    companion object {
        private fun getByInternalCode(key: String) = GuestStatus.values().find { it.internalCode == key }
        fun pack(guestStatus: GuestStatus): String = guestStatus.internalCode
        fun unpack(guestStatus: String): GuestStatus? = GuestStatus.getByInternalCode(guestStatus)
    }
}
