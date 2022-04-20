package com.example.dechproduct.hotelreservationapp.domain.usecase.utility

import com.example.dechproduct.hotelreservationapp.data.model.Booking
import com.example.dechproduct.hotelreservationapp.domain.repository.ReservationRepository
import com.example.dechproduct.hotelreservationapp.domain.repository.RoomRepository
import com.example.dechproduct.hotelreservationapp.domain.repository.UserRepository
import com.example.dechproduct.hotelreservationapp.util.Resource
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val userRepository: UserRepository) {
    //suspend operator fun invoke(keyword: String): Resource<MutableList<Booking>> = reservationRepository.searchReservation(keyword)
}