package com.example.dechproduct.hotelreservationapp.data.model.device

import com.example.dechproduct.hotelreservationapp.data.model.booking.BookingStatus
import com.example.dechproduct.hotelreservationapp.util.Constants
import com.google.gson.annotations.SerializedName

data class DeviceDTO(
    @SerializedName(Constants.API_DEVICE_KEY_ID)
    var deviceID: String?,
    @SerializedName(Constants.API_DEVICE_KEY_NAME)
    var displayName: String?,
    @SerializedName(Constants.API_DEVICE_KEY_TYPE)
    var type: String?,
    @SerializedName(Constants.API_DEVICE_KEY_SERIAL)
    var serial: String?,
    @SerializedName(Constants.API_DEVICE_KEY_OEM)
    var manufacturer: String?,
    @SerializedName(Constants.API_DEVICE_KEY_STATUS)
    var status: String?
){
    override fun toString(): String {
        return displayName.toString() + ":" + serial.toString()
    }

    fun toDevice(): Device {
        return Device(
            deviceID = deviceID,
            displayName = displayName,
            type = type,
            serial = serial,
            manufacturer = manufacturer,
            status = status?.let { DeviceStatus.unpack(it) },
        )
    }
}