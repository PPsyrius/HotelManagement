package com.example.dechproduct.hotelreservationapp.domain.usecase.auth

import com.example.dechproduct.hotelreservationapp.domain.repository.UserRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val userRepository: UserRepository) {
    //suspend operator fun invoke(keyword: String): Resource<MutableList<Booking>> = reservationRepository.searchReservation(keyword)
}