package com.example.dechproduct.hotelreservationapp.domain.usecase.checkout

import com.example.dechproduct.hotelreservationapp.data.model.Booking
import com.example.dechproduct.hotelreservationapp.data.model.Room
import com.example.dechproduct.hotelreservationapp.data.model.RoomStatus
import com.example.dechproduct.hotelreservationapp.domain.repository.RoomRepository
import com.example.dechproduct.hotelreservationapp.domain.usecase.reservation.EditReserveUseCase
import com.example.dechproduct.hotelreservationapp.domain.usecase.room.EditRoomUseCase
import com.example.dechproduct.hotelreservationapp.util.Constants
import com.example.dechproduct.hotelreservationapp.util.Resource
import javax.inject.Inject

class CheckOutGuestUseCase @Inject constructor(
    private val roomRepository: RoomRepository,
    private val editReserveUseCase: EditReserveUseCase,
    private val editRoomUseCase: EditRoomUseCase
) {
    suspend operator fun invoke(booking: Booking): Resource<Booking>{
        booking.guestRoom?.roomStatus = RoomStatus.CLEAN
        booking.guestRoom?.let { editRoomUseCase(it) }

        booking.guestRoom = Room()
        booking.guestStatus = Constants.GUEST_STATUS_CHECKOUT
        return editReserveUseCase(booking)
    }
}