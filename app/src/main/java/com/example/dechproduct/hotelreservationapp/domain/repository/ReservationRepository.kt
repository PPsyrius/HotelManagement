package com.example.dechproduct.hotelreservationapp.domain.repository

import com.example.dechproduct.hotelreservationapp.data.model.booking.Booking
import com.example.dechproduct.hotelreservationapp.data.model.booking.BookingStatus
import com.example.dechproduct.hotelreservationapp.util.Resource

interface ReservationRepository {
    suspend fun add(booking: Booking):Resource<Booking>
    suspend fun edit(booking: Booking):Resource<Booking>
    suspend fun remove(booking: Booking):Resource<Booking>
    suspend fun getByID(keyword: String):Resource<Booking>
    suspend fun searchByName(keyword: String, status: List<BookingStatus>):Resource<MutableList<Booking>>
    suspend fun searchByID(keyword: String, status: List<BookingStatus>):Resource<MutableList<Booking>>
    suspend fun searchByDate(keyword: String, status: List<BookingStatus>):Resource<MutableList<Booking>>
    suspend fun searchByRoomID(keyword: String, status: List<BookingStatus>):Resource<MutableList<Booking>>
    suspend fun populate(status: List<BookingStatus>):Resource<MutableList<Booking>>
}