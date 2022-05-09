package com.example.dechproduct.hotelreservationapp.domain.usecase.room

import com.example.dechproduct.hotelreservationapp.data.model.booking.BookingStatus
import com.example.dechproduct.hotelreservationapp.data.model.room.Room
import com.example.dechproduct.hotelreservationapp.data.model.room.RoomStatus
import com.example.dechproduct.hotelreservationapp.domain.repository.RoomRepository
import com.example.dechproduct.hotelreservationapp.util.Resource
import javax.inject.Inject

class PopulateRoomUseCase @Inject constructor(private val roomRepository: RoomRepository) {
    suspend operator fun invoke(arg: List<RoomStatus> = mutableListOf<RoomStatus>()): Resource<MutableList<Room>> =
        roomRepository.populate(arg)
}