package com.example.dechproduct.hotelreservationapp.data.model.booking

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class BookingStatus(
    var displayName: String,

    var internalCode: String,

    ) : Parcelable {
    CREATED("Reservation Created", "CRE"),
    RESERVED("Reservation Completed", "RSC"),
    GUARANTEED("Guaranteed Reserved", "GRR"),
    CHECK_IN("Check-In Completed", "CHI"),
    CHECK_OUT("Check-Out Completed", "CHO"),
    CANCEL("Guest Cancelled", "CAX"),
    ERROR("Error State", "ERR"),
    NONE("Default", "NON");

    companion object {
        private fun getByInternalCode(key: String) =
            BookingStatus.values().find { it.internalCode == key }

        fun pack(BookingStatus: BookingStatus): String = BookingStatus.internalCode
        fun unpack(guestStatus: String): BookingStatus? =
            BookingStatus.getByInternalCode(guestStatus)
    }
}
