package com.example.dechproduct.hotelreservationapp.domain.usecase.checkin

import com.example.dechproduct.hotelreservationapp.data.model.booking.Booking
import com.example.dechproduct.hotelreservationapp.data.model.room.Room
import com.example.dechproduct.hotelreservationapp.data.model.booking.BookingStatus
import com.example.dechproduct.hotelreservationapp.domain.repository.RoomRepository
import com.example.dechproduct.hotelreservationapp.domain.usecase.reservation.EditReserveUseCase
import com.example.dechproduct.hotelreservationapp.util.Resource
import javax.inject.Inject

class CheckInGuestUseCase @Inject constructor(
    private val roomRepository: RoomRepository,
    private val editReserveUseCase: EditReserveUseCase
) {
    suspend operator fun invoke(booking: Booking, room: Room): Resource<Booking> {
        booking.room = room
        booking.status = BookingStatus.CHECK_IN
        return editReserveUseCase(booking)
    }
}