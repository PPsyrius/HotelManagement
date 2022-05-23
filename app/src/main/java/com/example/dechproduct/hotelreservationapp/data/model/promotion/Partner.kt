package com.example.dechproduct.hotelreservationapp.data.model.promotion

import android.os.Parcelable
import com.example.dechproduct.hotelreservationapp.data.model.room.Feature
import com.example.dechproduct.hotelreservationapp.data.model.room.RoomStatus
import kotlinx.parcelize.Parcelize

@Parcelize
enum class Partner(
    var displayName: String,

    var internalCode: String,

    ) : Parcelable {
    VISA("Ready", "VI"),
    MASTERCARD("Guaranteed", "MC"),
    BANK_1("The Something Bank", "B1"),
    BANK_2("Bank of Something", "B2"),
    NONE("Default", "NON");

    companion object {
        private fun getByInternalCode(key: String) = Partner.values().find { it.internalCode == key }
        fun getByDisplayName(key: String) = Partner.values().find { it.displayName == key }
        fun pack(partner: Partner): String = partner.internalCode
        fun unpack(partner: String): Partner? = Partner.getByInternalCode(partner)

        fun toListOfStrings(partners: List<Partner>): List<String> {
            var parcel: MutableList<String> = mutableListOf<String>()
            for (partner in partners) {
                parcel.add(partner.internalCode)
            }
            return parcel
        }

        fun toListOfPartners(partners: List<String>): List<Partner> {
            var parcel: MutableList<Partner> = mutableListOf<Partner>()
            for (partner in partners) {
                Partner.getByInternalCode(partner)?.let { parcel.add(it) }
            }
            return parcel
        }
    }
}