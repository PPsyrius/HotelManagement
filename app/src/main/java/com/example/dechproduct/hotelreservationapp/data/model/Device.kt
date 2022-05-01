package com.example.dechproduct.hotelreservationapp.data.model

import android.os.Parcelable
import com.example.dechproduct.hotelreservationapp.data.model.utility.room.Feature
import com.example.dechproduct.hotelreservationapp.data.model.utility.room.RoomStatus
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Device(
    var displayName: String? = "",
    var deviceSerial: String? = "",
    var manufacturer: String? = "",
    var deviceStatus: String? = "",
    var roomAssigned: String? = ""
) : Parcelable {
    override fun toString(): String {
        return displayName.toString() + ":" + deviceSerial.toString()
    }

    fun toDeviceDTO(): DeviceDTO {
        return DeviceDTO(
            displayName = displayName,
            deviceSerial = deviceSerial,
            manufacturer = manufacturer,
            deviceStatus = deviceStatus,
            roomAssigned = roomAssigned
        )
    }

    companion object {
        fun pack(devices: List<Device>): List<String> {
            var parcel: MutableList<String> = mutableListOf<String>()
            for (device in devices) {
                parcel.add(device.deviceSerial.toString())
            }
            return parcel
        }

        fun unpack(devices: List<String>): List<Device> {
            var parcel: MutableList<Device> = mutableListOf<Device>()
            for (device in devices) {
                parcel.add(Device(deviceSerial = device))
            }
            return parcel
        }
    }
}