package com.example.dechproduct.hotelreservationapp.data.model.device

import android.os.Parcelable
import com.example.dechproduct.hotelreservationapp.data.model.booking.BookingStatus
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

//    companion object {
//        fun pack(devices: List<Device>): List<String> {
//            var parcel: MutableList<String> = mutableListOf<String>()
//            for (device in devices) {
//                parcel.add(device.serial.toString())
//            }
//            return parcel
//        }
//
//        fun unpack(devices: List<String>): List<Device> {
//            var parcel: MutableList<Device> = mutableListOf<Device>()
//            for (device in devices) {
//                parcel.add(Device(
//                    deviceID = ,
//                    displayName = ,
//                    type = ,
//                    serial = device,
//                    manufacturer = ,
//                    status =
//                ))
//            }
//            return parcel
//        }
//    }
}