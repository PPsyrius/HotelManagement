package com.example.dechproduct.hotelreservationapp.domain.usecase.checkin

import com.example.dechproduct.hotelreservationapp.data.model.Booking
import com.example.dechproduct.hotelreservationapp.data.model.Room
import com.example.dechproduct.hotelreservationapp.data.model.utility.booking.GuestStatus
import com.example.dechproduct.hotelreservationapp.domain.repository.RoomRepository
import com.example.dechproduct.hotelreservationapp.domain.usecase.reservation.EditReserveUseCase
import com.example.dechproduct.hotelreservationapp.domain.usecase.reservation.SearchReserveByNameUseCase
import com.example.dechproduct.hotelreservationapp.util.Constants
import com.example.dechproduct.hotelreservationapp.util.Resource
import javax.inject.Inject

class CheckInGuestUseCase @Inject constructor(
    private val roomRepository: RoomRepository,
    private val editReserveUseCase: EditReserveUseCase
) {
    suspend operator fun invoke(booking: Booking, room: Room): Resource<Booking> {
        booking.guestRoom = room
        booking.guestStatus = GuestStatus.CHECK_IN
        return editReserveUseCase(booking)
    }
}