package com.example.dechproduct.hotelreservationapp.domain.usecase.reservation

import com.example.dechproduct.hotelreservationapp.data.model.Booking
import com.example.dechproduct.hotelreservationapp.data.model.utility.booking.GuestStatus
import com.example.dechproduct.hotelreservationapp.domain.repository.ReservationRepository
import com.example.dechproduct.hotelreservationapp.util.Resource
import javax.inject.Inject

class PopulateReserveUseCase @Inject constructor(private val reservationRepository: ReservationRepository) {
    suspend operator fun invoke(arg: GuestStatus = GuestStatus.NONE): Resource<MutableList<Booking>> =
        reservationRepository.populate(arg)
}