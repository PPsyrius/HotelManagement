package com.example.dechproduct.hotelreservationapp.domain.usecase.checkin

import com.example.dechproduct.hotelreservationapp.data.model.Booking
import com.example.dechproduct.hotelreservationapp.domain.repository.ReservationRepository
import com.example.dechproduct.hotelreservationapp.domain.repository.RoomRepository
import com.example.dechproduct.hotelreservationapp.util.Resource
import javax.inject.Inject

class CheckInFromReservationUseCase @Inject constructor(
    private val reservationRepository: ReservationRepository, private val roomRepository: RoomRepository) {
    suspend operator fun invoke(keyword: String): Resource<MutableList<Booking>> = reservationRepository.searchReservation(keyword)
}