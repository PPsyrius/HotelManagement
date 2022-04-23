package com.example.dechproduct.hotelreservationapp.domain.repository

import com.example.dechproduct.hotelreservationapp.data.model.Feature
import com.example.dechproduct.hotelreservationapp.data.model.Room
import com.example.dechproduct.hotelreservationapp.data.model.RoomStatus
import com.example.dechproduct.hotelreservationapp.util.Resource

interface RoomRepository {
    suspend fun markRoom(room: Room, roomStatus: RoomStatus):Resource<Room>
    suspend fun searchRoom(keyword: String):Resource<MutableList<Room>>
}