package com.example.dechproduct.hotelreservationapp.data.model

import android.os.Parcelable
import com.example.dechproduct.hotelreservationapp.data.model.utility.room.BedType
import com.example.dechproduct.hotelreservationapp.data.model.utility.room.Feature
import com.example.dechproduct.hotelreservationapp.data.model.utility.room.RoomStatus
import com.example.dechproduct.hotelreservationapp.data.model.utility.room.RoomType
import kotlinx.parcelize.Parcelize

@Parcelize
data class Room(
    var roomID: String? = null,
    var roomType: RoomType? = null,
    var roomBeds: BedType? = null,
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
) : Parcelable {
    fun toRoomDTO(): RoomDTO {
        return RoomDTO(
            roomID = roomID,
            roomType = roomType?.let { RoomType.pack(it) },
            roomBeds = roomBeds?.let { BedType.pack(it) },
            maxCap = maxCap,
            posFloor = posFloor,
            features = features?.let { Feature.pack(it) },
            addonBed = addonBed,
            breakfast = breakfast,
            smoking = smoking,
            roomStatus = roomStatus?.let { RoomStatus.pack(it) },
            isWalking = isWalking,
            deviceList = deviceList?.let { Device.pack(it) },
            roomPrice = roomPrice,
        )
    }
}