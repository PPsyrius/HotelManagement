package com.example.dechproduct.hotelreservationapp.domain.repository

import com.example.dechproduct.hotelreservationapp.data.model.room.*
import com.example.dechproduct.hotelreservationapp.util.Resource

interface RoomRepository {
    suspend fun editRoom(room: Room): Resource<Room>
    suspend fun searchRoomByID(keyword: String): Resource<MutableList<Room>>
    suspend fun getRoomByID(keyword: String): Resource<Room>
    suspend fun searchRoom(
        keyword: String,
        roomType: RoomType,
        bedType: BedType,
        features: List<Feature>,
        smoking: Boolean,
        status: List<RoomStatus>,
        occupancy: Occupancy?
    ): Resource<MutableList<Room>>
    suspend fun populate(status: List<RoomStatus>): Resource<MutableList<Room>>
}