package com.example.dechproduct.hotelreservationapp.data.model.room

import android.os.Parcelable
import com.example.dechproduct.hotelreservationapp.data.model.device.Device
import kotlinx.parcelize.Parcelize

@Parcelize
data class Room(
    var roomID: String? = null,
    var type: RoomType? = null,
    var beds: BedType? = null,
    var maxCap: Int? = null,
    var floor: String? = null,
    var features: List<Feature>? = null,
    var canAddonBed: Boolean? = null,
    var smoking: Boolean? = null,
    var status: RoomStatus? = null,
    var occupancy: List<Occupancy>? = null,
    var isWalking: Boolean? = null,
    var device: List<Device>? = null,
    var price: Double? = null,
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
            device = device,
            price = price,
        )
    }
}