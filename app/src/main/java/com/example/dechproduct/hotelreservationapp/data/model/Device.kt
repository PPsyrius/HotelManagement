package com.example.dechproduct.hotelreservationapp.data.model

import android.os.Parcelable
import com.example.dechproduct.hotelreservationapp.data.model.utility.room.Feature
import com.example.dechproduct.hotelreservationapp.data.model.utility.room.RoomStatus
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Device(
    var deviceName: String? = "",
    var deviceSerial: String? = "",
): Parcelable{
    override fun toString(): String {
        return deviceName.toString()
    }
    companion object{
        fun pack(devices: List<Device>): List<String> {
            var parcel: MutableList<String> = mutableListOf<String>()
            for (device in devices) {
                device.deviceSerial?.let { parcel.add(it) }
            }
            return parcel
        }
//TODO: Decide whether to keep device name in database?
        fun unpack(devices: List<String>): List<Device> {
            var parcel: MutableList<Device> = mutableListOf<Device>()
            for (device in devices) {
                device?.let { parcel.add(Device(deviceSerial = it)) }
            }
            return parcel
        }
    }
}