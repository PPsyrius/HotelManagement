package com.example.dechproduct.hotelreservationapp.data.model

import android.os.Parcelable
import com.example.dechproduct.hotelreservationapp.data.model.utility.room.Feature
import com.example.dechproduct.hotelreservationapp.data.model.utility.room.RoomStatus
import com.example.dechproduct.hotelreservationapp.util.Constants
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class DeviceDTO(
    @SerializedName(Constants.API_DEVICE_KEY_NAME)
    var displayName: String?,
    @SerializedName(Constants.API_DEVICE_KEY_SERIAL)
    var deviceSerial: String?,
    @SerializedName(Constants.API_DEVICE_KEY_OEM)
    var manufacturer: String?,
    @SerializedName(Constants.API_DEVICE_KEY_STAT)
    var deviceStatus: String?,
    @SerializedName(Constants.API_DEVICE_KEY_ASSIGN)
    var roomAssigned: String?
){
    override fun toString(): String {
        return displayName.toString() + ":" + deviceSerial.toString()
    }

    fun toDevice(): Device{
        return Device(
            displayName = displayName,
            deviceSerial = deviceSerial,
            manufacturer = manufacturer,
            deviceStatus = deviceStatus,
            roomAssigned = roomAssigned
        )
    }
}