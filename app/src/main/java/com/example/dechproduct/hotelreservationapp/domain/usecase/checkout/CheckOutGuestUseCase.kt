package com.example.dechproduct.hotelreservationapp.domain.usecase.checkout

import com.example.dechproduct.hotelreservationapp.data.model.Room
import com.example.dechproduct.hotelreservationapp.domain.repository.ReservationRepository
import com.example.dechproduct.hotelreservationapp.domain.repository.RoomRepository
import com.example.dechproduct.hotelreservationapp.util.Resource
import javax.inject.Inject

class CheckOutGuestUseCase @Inject constructor(
    private val reservationRepository: ReservationRepository, private val roomRepository: RoomRepository) {
    suspend operator fun invoke(keyword: String): Resource<MutableList<Room>> = roomRepository.searchRoom(keyword)
}