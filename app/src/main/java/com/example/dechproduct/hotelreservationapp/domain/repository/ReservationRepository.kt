package com.example.dechproduct.hotelreservationapp.domain.repository

import com.example.dechproduct.hotelreservationapp.data.model.Booking
import com.example.dechproduct.hotelreservationapp.util.Resource

interface ReservationRepository {
    suspend fun addReservation(booking:Booking):Resource<Booking>
    suspend fun editReservation(booking:Booking):Resource<Booking>
    suspend fun removeReservation(booking: Booking):Resource<Booking>
    suspend fun searchReservation(keyword: String):Resource<MutableList<Booking>>
    suspend fun populateReservation():Resource<MutableList<Booking>>
}