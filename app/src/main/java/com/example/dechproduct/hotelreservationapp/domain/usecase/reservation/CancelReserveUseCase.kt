package com.example.dechproduct.hotelreservationapp.domain.usecase.reservation

import com.example.dechproduct.hotelreservationapp.data.model.booking.Booking
import com.example.dechproduct.hotelreservationapp.data.model.booking.BookingStatus
import com.example.dechproduct.hotelreservationapp.data.model.room.Occupancy
import com.example.dechproduct.hotelreservationapp.data.model.room.Room
import com.example.dechproduct.hotelreservationapp.data.model.room.RoomStatus
import com.example.dechproduct.hotelreservationapp.domain.repository.ReservationRepository
import com.example.dechproduct.hotelreservationapp.domain.usecase.room.EditRoomUseCase
import com.example.dechproduct.hotelreservationapp.util.Resource
import javax.inject.Inject

class CancelReserveUseCase @Inject constructor(
    private val editReserveUseCase: EditReserveUseCase,
    private val editRoomUseCase: EditRoomUseCase
) {
    suspend operator fun invoke(booking: Booking): Resource<Booking> {
        booking.room?.status = RoomStatus.READY
        var discarder = mutableListOf<Occupancy>()
        for (item in booking.room?.occupancy!!) {
            if (booking.arrivalDate == item.arrivalDate &&
                booking.departDate == item.departDate
            ) {
                discarder.add(item)
                break
            }
        }
        booking.room!!.occupancy!!.removeAll(discarder)
        booking.room?.let { editRoomUseCase(it) }

        booking.room = Room()
        booking.status = BookingStatus.CANCEL
        return editReserveUseCase(booking)
    }
}