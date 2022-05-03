package com.example.dechproduct.hotelreservationapp.domain.usecase.checkout

import com.example.dechproduct.hotelreservationapp.data.model.booking.Booking
import com.example.dechproduct.hotelreservationapp.data.model.room.RoomStatus
import com.example.dechproduct.hotelreservationapp.domain.repository.RoomRepository
import com.example.dechproduct.hotelreservationapp.domain.usecase.reservation.EditReserveUseCase
import com.example.dechproduct.hotelreservationapp.domain.usecase.room.EditRoomUseCase
import com.example.dechproduct.hotelreservationapp.util.Resource
import javax.inject.Inject

class ExtendStayUseCase @Inject constructor(
    private val roomRepository: RoomRepository,
    private val editReserveUseCase: EditReserveUseCase,
    private val editRoomUseCase: EditRoomUseCase
) {
    suspend operator fun invoke(booking: Booking): Resource<Booking>{
        booking.guestRoom?.roomStatus = RoomStatus.SOF_CLEAN
        booking.guestRoom?.let { editRoomUseCase(it) }

        return editReserveUseCase(booking)
    }
}