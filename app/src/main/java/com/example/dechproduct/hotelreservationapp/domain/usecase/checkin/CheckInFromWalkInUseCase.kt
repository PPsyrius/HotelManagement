package com.example.dechproduct.hotelreservationapp.domain.usecase.checkin

import com.example.dechproduct.hotelreservationapp.data.model.Booking
import com.example.dechproduct.hotelreservationapp.domain.repository.ReservationRepository
import com.example.dechproduct.hotelreservationapp.domain.repository.RoomRepository
import com.example.dechproduct.hotelreservationapp.domain.usecase.reservation.AddReserveUseCase
import com.example.dechproduct.hotelreservationapp.util.Resource
import javax.inject.Inject

class CheckInFromWalkInUseCase @Inject constructor(
    private val roomRepository: RoomRepository, private val addReserveUseCase: AddReserveUseCase
) {
    suspend operator fun invoke(): String {
        return "Resource<Booking>"
    }
}