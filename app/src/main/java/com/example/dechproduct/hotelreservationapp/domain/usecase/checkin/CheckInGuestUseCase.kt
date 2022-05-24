package com.example.dechproduct.hotelreservationapp.domain.usecase.checkin

import com.example.dechproduct.hotelreservationapp.data.model.booking.Booking
import com.example.dechproduct.hotelreservationapp.data.model.room.Room
import com.example.dechproduct.hotelreservationapp.data.model.booking.BookingStatus
import com.example.dechproduct.hotelreservationapp.data.model.room.Occupancy
import com.example.dechproduct.hotelreservationapp.data.model.room.OccupancyStatus
import com.example.dechproduct.hotelreservationapp.data.model.room.RoomStatus
import com.example.dechproduct.hotelreservationapp.domain.repository.RoomRepository
import com.example.dechproduct.hotelreservationapp.domain.usecase.reservation.EditReserveUseCase
import com.example.dechproduct.hotelreservationapp.domain.usecase.room.MarkRoomUseCase
import com.example.dechproduct.hotelreservationapp.util.Resource
import javax.inject.Inject

class CheckInGuestUseCase @Inject constructor(
    private val markRoomUseCase: MarkRoomUseCase,
    private val editReserveUseCase: EditReserveUseCase
) {
    suspend operator fun invoke(booking: Booking, room: Room): Resource<Booking> {
        if(booking.room?.roomID != room.roomID){
            booking.room?.let { markRoomUseCase(it, RoomStatus.READY) }
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
            booking.room = room
            booking.room!!.occupancy!!.add(Occupancy(booking.arrivalDate, booking.departDate, OccupancyStatus.TALLY))
        }

        booking.room?.let { markRoomUseCase(it, RoomStatus.OCCUPIED) }
        booking.status = BookingStatus.CHECK_IN
        return editReserveUseCase(booking)
    }
}