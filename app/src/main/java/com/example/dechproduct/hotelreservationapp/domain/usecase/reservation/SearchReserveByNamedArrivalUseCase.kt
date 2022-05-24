package com.example.dechproduct.hotelreservationapp.domain.usecase.reservation

import com.example.dechproduct.hotelreservationapp.data.model.booking.Booking
import com.example.dechproduct.hotelreservationapp.data.model.booking.BookingStatus
import com.example.dechproduct.hotelreservationapp.domain.repository.ReservationRepository
import com.example.dechproduct.hotelreservationapp.util.Resource
import javax.inject.Inject

class SearchReserveByNamedArrivalUseCase @Inject constructor(private val reservationRepository: ReservationRepository) {
    suspend operator fun invoke(
        name: String,
        date: String,
        arg: List<BookingStatus> = mutableListOf<BookingStatus>()
    ): Resource<MutableList<Booking>> =
        reservationRepository.searchByNamedArrivalDate(name, date, arg)
}