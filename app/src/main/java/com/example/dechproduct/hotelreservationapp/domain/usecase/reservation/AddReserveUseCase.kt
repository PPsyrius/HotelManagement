package com.example.dechproduct.hotelreservationapp.domain.usecase.reservation

import com.example.dechproduct.hotelreservationapp.data.model.booking.Booking
import com.example.dechproduct.hotelreservationapp.domain.repository.ReservationRepository
import com.example.dechproduct.hotelreservationapp.util.Resource
import javax.inject.Inject

class AddReserveUseCase @Inject constructor(private val reservationRepository: ReservationRepository) {
    suspend operator fun invoke(booking: Booking): Resource<Booking> {
        //TODO: Add reservation with random room
        //TODO: Checks add reservation date for crash
        //TODO: On add reservation, if select type and full -> show msg
        booking.bookingID = System.currentTimeMillis().toString()
        return reservationRepository.add(booking)
    }
}