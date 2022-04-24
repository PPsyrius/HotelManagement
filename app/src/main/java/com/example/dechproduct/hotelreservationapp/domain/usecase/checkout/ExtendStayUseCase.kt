package com.example.dechproduct.hotelreservationapp.domain.usecase.checkout

import com.example.dechproduct.hotelreservationapp.data.model.Booking
import com.example.dechproduct.hotelreservationapp.domain.repository.ReservationRepository
import com.example.dechproduct.hotelreservationapp.domain.repository.RoomRepository
import com.example.dechproduct.hotelreservationapp.domain.usecase.reservation.EditReserveUseCase
import com.example.dechproduct.hotelreservationapp.util.Resource
import javax.inject.Inject

class ExtendStayUseCase @Inject constructor(
    private val roomRepository: RoomRepository,
    private val editReserveUseCase: EditReserveUseCase
) {
    suspend operator fun invoke(booking: Booking): Resource<Booking>{
        return editReserveUseCase(booking)
    }
}