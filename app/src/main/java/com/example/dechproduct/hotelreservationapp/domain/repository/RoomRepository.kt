package com.example.dechproduct.hotelreservationapp.domain.repository

import com.example.dechproduct.hotelreservationapp.data.model.room.Room
import com.example.dechproduct.hotelreservationapp.data.model.room.BedType
import com.example.dechproduct.hotelreservationapp.data.model.room.Feature
import com.example.dechproduct.hotelreservationapp.data.model.room.RoomStatus
import com.example.dechproduct.hotelreservationapp.data.model.room.RoomType
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