package com.example.dechproduct.hotelreservationapp.data.model.device

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class DeviceStatus(
    var displayName: String,

    var internalCode: String,

    ) : Parcelable {
    NORMAL("Device Functioning.", "NM"),
    BROKEN("Needs Fixing.", "NF"),
    NONE("Default", "NON");

    companion object {
        private fun getByInternalCode(key: String) =
            DeviceStatus.values().find { it.internalCode == key }

        fun pack(DeviceStatus: DeviceStatus): String = DeviceStatus.internalCode
        fun unpack(deviceStatus: String): DeviceStatus? =
            DeviceStatus.getByInternalCode(deviceStatus)
    }
}
