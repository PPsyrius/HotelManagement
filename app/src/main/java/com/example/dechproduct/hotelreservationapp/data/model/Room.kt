package com.example.dechproduct.hotelreservationapp.data.model

import android.os.Parcelable
import com.example.dechproduct.hotelreservationapp.util.Constants
import kotlinx.parcelize.Parcelize

@Parcelize
data class Room(
    var roomID: String,
    var roomType: String = Constants.ROOM_TYP_STANDARD,
    var roomBeds: String = Constants.ROOM_BED_SINGLE,
    var maxCap: Int = Constants.ROOM_MAX_CAPACITY,
    var posFloor: String?,
    var posMap: String = Constants.ROOM_LOC_NORMAL,
    var roomStatus: RoomStatus,
    var isWalking: Boolean,
    var deviceList: String?,
    var roomPrice: Double,
): Parcelable