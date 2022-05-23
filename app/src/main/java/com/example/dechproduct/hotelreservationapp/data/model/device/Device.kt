package com.example.dechproduct.hotelreservationapp.data.model.device

import android.os.Parcelable
import com.example.dechproduct.hotelreservationapp.util.Constants
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Device(

    var deviceID: String? = "",

    var displayName: String? = "",

    var type: String? = "",

    var serial: String? = "",

    var manufacturer: String? = "",

    var status: DeviceStatus? = DeviceStatus.NONE
) : Parcelable {
    override fun toString(): String {
        return displayName.toString() + ":" + serial.toString()
    }

    fun toDeviceDTO(): DeviceDTO {
        return DeviceDTO(
            deviceID = deviceID,
            displayName = displayName,
            type = type,
            serial = serial,
            manufacturer = manufacturer,
            status = status?.let { DeviceStatus.pack(it) },
        )
    }

    companion object{
        fun toListOfDeviceDTO(devices: List<Device>):MutableList<DeviceDTO>{
            var parcel: MutableList<DeviceDTO> = mutableListOf<DeviceDTO>()
            for (device in devices) {
                parcel.add(device.toDeviceDTO())
            }
            return parcel
        }
    }
}