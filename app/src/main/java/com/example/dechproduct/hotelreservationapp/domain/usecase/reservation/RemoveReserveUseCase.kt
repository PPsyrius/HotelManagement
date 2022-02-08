package com.example.dechproduct.hotelreservationapp.domain.usecase.reservation

import com.example.dechproduct.hotelreservationapp.data.model.Booking
import com.example.dechproduct.hotelreservationapp.domain.repository.ReservationRepository
import com.example.dechproduct.hotelreservationapp.util.Resource
import javax.inject.Inject

class RemoveReserveUseCase @Inject constructor(private val reservationRepository: ReservationRepository) {
    suspend operator fun invoke(booking: Booking): Resource<Booking> = reservationRepository.removeReservation(booking)
}