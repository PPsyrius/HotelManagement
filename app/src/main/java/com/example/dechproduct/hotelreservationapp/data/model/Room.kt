package com.example.dechproduct.hotelreservationapp.data.model

import android.os.Parcelable
import com.example.dechproduct.hotelreservationapp.util.Constants
import kotlinx.parcelize.Parcelize

@Parcelize
data class Room(
    var roomID: String? = null,
    var roomType: String? = null,
    var roomBeds: String? = null,
    var maxCap: Int? = null,
    var posFloor: String? = null,
    var features: List<Feature>? = null,
    var addonBed: Boolean? = null,
    var breakfast: Boolean? = null,
    var smoking: Boolean? = null,
    var roomStatus: RoomStatus? = null,
    var isWalking: Boolean? = null,
    var deviceList: List<Device>? = null,
    var roomPrice: Double? = null,
): Parcelable