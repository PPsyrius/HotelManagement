package com.example.dechproduct.hotelreservationapp.data.model

import com.example.dechproduct.hotelreservationapp.data.model.utility.room.BedType
import com.example.dechproduct.hotelreservationapp.data.model.utility.room.Feature
import com.example.dechproduct.hotelreservationapp.data.model.utility.room.RoomStatus
import com.example.dechproduct.hotelreservationapp.data.model.utility.room.RoomType
import com.example.dechproduct.hotelreservationapp.util.Constants
import com.google.gson.annotations.SerializedName

data class RoomDTO(
    @SerializedName(Constants.API_ROOM_KEY_ID)
    var roomID: String?,
    @SerializedName(Constants.API_ROOM_KEY_TYPE)
    var roomType: String?,
    @SerializedName(Constants.API_ROOM_KEY_BEDS)
    var roomBeds: String?,
    @SerializedName(Constants.API_ROOM_KEY_CAP)
    var maxCap: Int?,
    @SerializedName(Constants.API_ROOM_KEY_FLOOR)
    var posFloor: String?,
    @SerializedName(Constants.API_ROOM_KEY_FEATURES)
    var features: List<String>?,
    @SerializedName(Constants.API_ROOM_KEY_ADDONBED)
    var canAddonBed: Boolean?,
    @SerializedName(Constants.API_ROOM_KEY_SMOKING)
    var smoking: Boolean?,
    @SerializedName(Constants.API_ROOM_KEY_STAT)
    var roomStatus: String?,
    @SerializedName(Constants.API_ROOM_KEY_WALK)
    var isWalking: Boolean?,
    @SerializedName(Constants.API_ROOM_KEY_DEVICES)
    var deviceList: List<String>?,
    @SerializedName(Constants.API_ROOM_KEY_PRICE)
    var roomPrice: Double?,
) {
    fun toRoom(): Room {
        return Room(
            roomID = roomID,
            roomType = roomType?.let { RoomType.unpack(it) },
            roomBeds = roomBeds?.let { BedType.unpack(it) },
            maxCap = maxCap,
            posFloor = posFloor,
            features = features?.let { Feature.unpack(it) },
            canAddonBed = canAddonBed,
            smoking = smoking,
            roomStatus = roomStatus?.let { RoomStatus.unpack(it) },
            isWalking = isWalking,
            deviceList = deviceList?.let { Device.unpack(it) },
            roomPrice = roomPrice,
        )
    }
}