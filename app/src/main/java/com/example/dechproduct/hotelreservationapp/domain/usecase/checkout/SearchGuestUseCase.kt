package com.example.dechproduct.hotelreservationapp.domain.usecase.checkout

import com.example.dechproduct.hotelreservationapp.data.model.Booking
import com.example.dechproduct.hotelreservationapp.data.model.utility.booking.GuestStatus
import com.example.dechproduct.hotelreservationapp.domain.repository.ReservationRepository
import com.example.dechproduct.hotelreservationapp.domain.repository.RoomRepository
import com.example.dechproduct.hotelreservationapp.util.Resource
import javax.inject.Inject

class SearchGuestUseCase @Inject constructor(
    private val roomRepository: RoomRepository,
    private val reservationRepository: ReservationRepository
) {
    suspend operator fun invoke(keyword: String, arg: GuestStatus = GuestStatus.NONE): Resource<MutableList<Booking>> =
        reservationRepository.searchByRoomID(keyword, arg)
}