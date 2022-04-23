package com.example.dechproduct.hotelreservationapp.domain.usecase.com

import com.example.dechproduct.hotelreservationapp.data.model.Booking
import com.example.dechproduct.hotelreservationapp.domain.repository.ReservationRepository
import com.example.dechproduct.hotelreservationapp.util.Resource
import javax.inject.Inject

class GetReserveByIDUseCase @Inject constructor(private val reservationRepository: ReservationRepository) {
    suspend operator fun invoke(keyword: String): Resource<Booking> =
        reservationRepository.getByID(keyword)
}