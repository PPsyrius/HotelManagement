package com.example.dechproduct.hotelreservationapp.domain.usecase.room

import com.example.dechproduct.hotelreservationapp.data.model.Room
import com.example.dechproduct.hotelreservationapp.domain.repository.RoomRepository
import com.example.dechproduct.hotelreservationapp.util.Resource
import javax.inject.Inject

class SearchRoomUseCase @Inject constructor(private val roomRepository: RoomRepository) {
    suspend operator fun invoke(keyword: String): Resource<MutableList<Room>> = roomRepository.searchRoom(keyword)
}