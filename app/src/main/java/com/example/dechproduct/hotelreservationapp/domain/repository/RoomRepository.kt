package com.example.dechproduct.hotelreservationapp.domain.repository

import com.example.dechproduct.hotelreservationapp.data.model.Room
import com.example.dechproduct.hotelreservationapp.data.model.utility.room.BedType
import com.example.dechproduct.hotelreservationapp.data.model.utility.room.Feature
import com.example.dechproduct.hotelreservationapp.data.model.utility.room.RoomStatus
import com.example.dechproduct.hotelreservationapp.data.model.utility.room.RoomType
import com.example.dechproduct.hotelreservationapp.util.Resource

interface RoomRepository {
    suspend fun editRoom(room: Room): Resource<Room>
    suspend fun getRoom(keyword: String, status: RoomStatus): Resource<MutableList<Room>>
    suspend fun searchRoom(
        roomType: RoomType,
        bedType: BedType,
        features: List<Feature>,
        smoking: Boolean,
        status: RoomStatus
    ): Resource<MutableList<Room>>
    //suspend fun searchByTag(keyword: String): Resource<MutableList<Room>>
}