package com.example.dechproduct.hotelreservationapp.data.model.room

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class Feature(
    var displayName: String,

    var internalCode: String,

    ) : Parcelable {
    AWAY_FROM_LIFT("Away from Elevator", "AL"),
    CITY_VIEW("City View", "CV"),
    SEA_VIEW("Sea View", "SV"),
    MOUNTAIN_VIEW("Mountain View", "MV"),
    CORNER_ROOM("Corner Room", "CR"),
    PETS_ALLOWED("Pets Allowed", "PL");

    companion object {
        private fun getByInternalCode(key: String) = Feature.values().find { it.internalCode == key }
        fun pack(features: List<Feature>): List<String> {
            var parcel: MutableList<String> = mutableListOf<String>()
            for (feature in features) {
                parcel.add(feature.internalCode)
            }
            return parcel
        }

        fun unpack(features: List<String>): List<Feature> {
            var parcel: MutableList<Feature> = mutableListOf<Feature>()
            for (feature in features) {
                getByInternalCode(feature)?.let { parcel.add(it) }
            }
            return parcel
        }
    }
}