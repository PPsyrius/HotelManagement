package com.example.dechproduct.hotelreservationapp.domain.usecase.reservation

import com.example.dechproduct.hotelreservationapp.data.model.Booking
import com.example.dechproduct.hotelreservationapp.data.model.utility.booking.GuestStatus
import com.example.dechproduct.hotelreservationapp.domain.repository.ReservationRepository
import com.example.dechproduct.hotelreservationapp.util.Resource
import javax.inject.Inject

class SearchReserveByNameUseCase @Inject constructor(private val reservationRepository: ReservationRepository) {
    suspend operator fun invoke(keyword: String, arg: GuestStatus = GuestStatus.NONE): Resource<MutableList<Booking>> =
        reservationRepository.searchByName(keyword, arg)
}