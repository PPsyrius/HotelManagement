package com.example.dechproduct.hotelreservationapp.data.model

import com.example.dechproduct.hotelreservationapp.util.Constants
import com.google.gson.annotations.SerializedName

data class RoomDTO(
    @SerializedName(Constants.API_ROOM_KEY_ID)
    var roomID: String,
    @SerializedName(Constants.API_ROOM_KEY_TYPE)
    var roomType: String,
    @SerializedName(Constants.API_ROOM_KEY_BEDS)
    var roomBeds: String,
    @SerializedName(Constants.API_ROOM_KEY_CAP)
    var maxCap: Int,
    @SerializedName(Constants.API_ROOM_KEY_FLOOR)
    var posFloor: String?,
    @SerializedName(Constants.API_ROOM_KEY_FEATURES)
    var features: List<Feature>,
    @SerializedName(Constants.API_ROOM_KEY_SMOKING)
    var smoking: Boolean,
    @SerializedName(Constants.API_ROOM_KEY_STAT)
    var roomStatus: RoomStatus,
    @SerializedName(Constants.API_ROOM_KEY_WALK)
    var isWalking: Boolean,
    @SerializedName(Constants.API_ROOM_KEY_DEVICES)
    var deviceList: List<Device>,
    @SerializedName(Constants.API_ROOM_KEY_PRICE)
    var roomPrice: Double,
) {
    fun toRoom(): Room{
        return Room(
            roomID = roomID,
            roomType = roomType,
            roomBeds = roomBeds,
            maxCap = maxCap,
            posFloor = posFloor,
            features = features,
            smoking = smoking,
            roomStatus = roomStatus,
            isWalking = isWalking,
            deviceList = deviceList,
            roomPrice = roomPrice,
        )
    }
}