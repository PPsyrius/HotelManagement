package com.example.dechproduct.hotelreservationapp.data.model.room

import com.example.dechproduct.hotelreservationapp.data.model.device.Device
import com.example.dechproduct.hotelreservationapp.util.Constants
import com.google.gson.annotations.SerializedName

data class RoomDTO(
    @SerializedName(Constants.API_ROOM_KEY_ID)
    var roomID: String?,
    @SerializedName(Constants.API_ROOM_KEY_TYPE)
    var type: String?,
    @SerializedName(Constants.API_ROOM_KEY_BEDS)
    var beds: String?,
    @SerializedName(Constants.API_ROOM_KEY_CAP)
    var maxCap: Int?,
    @SerializedName(Constants.API_ROOM_KEY_FLOOR)
    var floor: String?,
    @SerializedName(Constants.API_ROOM_KEY_FEATURES)
    var features: List<String>?,
    @SerializedName(Constants.API_ROOM_KEY_ADDBED)
    var canAddonBed: Boolean?,
    @SerializedName(Constants.API_ROOM_KEY_SMOKING)
    var smoking: Boolean?,
    @SerializedName(Constants.API_ROOM_KEY_STATUS)
    var status: String?,
    @SerializedName(Constants.API_ROOM_KEY_WALKING)
    var isWalking: Boolean?,
    @SerializedName(Constants.API_ROOM_KEY_DEVICES)
    var device: List<Device>?,
    @SerializedName(Constants.API_ROOM_KEY_PRICE)
    var price: Double?,
) {
    fun toRoom(): Room {
        return Room(
            roomID = roomID,
            type = type?.let { RoomType.unpack(it) },
            beds = beds?.let { BedType.unpack(it) },
            maxCap = maxCap,
            floor = floor,
            features = features?.let { Feature.unpack(it) },
            canAddonBed = canAddonBed,
            smoking = smoking,
            status = status?.let { RoomStatus.unpack(it) },
            isWalking = isWalking,
            device = device,
            price = price,
        )
    }
}