package com.example.dechproduct.hotelreservationapp.data.model.room

import android.os.Parcelable
import com.example.dechproduct.hotelreservationapp.data.model.device.Device
import kotlinx.parcelize.Parcelize

@Parcelize
data class Room(
    var roomID: String? = "",
    var type: RoomType? = RoomType.NONE,
    var beds: BedType? = BedType.NONE,
    var maxCap: Int? = 0,
    var floor: String? = "",
    var features: List<Feature>? = listOf<Feature>(),
    var canAddonBed: Boolean? = true,
    var smoking: Boolean? = false,
    var status: RoomStatus? = RoomStatus.NONE,
    var occupancy: MutableList<Occupancy>? = mutableListOf<Occupancy>(),
    var isWalking: Boolean? = false,
    var device: List<Device>? = listOf<Device>(),
    var price: Double? = 0.0,
) : Parcelable {
    fun toRoomDTO(): RoomDTO {
        return RoomDTO(
            roomID = roomID,
            type = type?.let { RoomType.pack(it) },
            beds = beds?.let { BedType.pack(it) },
            maxCap = maxCap,
            floor = floor,
            features = features?.let { Feature.pack(it) },
            canAddonBed = canAddonBed,
            smoking = smoking,
            status = status?.let { RoomStatus.pack(it) },
            occupancy = occupancy?.let { Occupancy.toListOfOccupancyDTO(it) },
            isWalking = isWalking,
            device = device?.let { Device.toListOfDeviceDTO(it) },
            price = price,
        )
    }
}