package com.example.dechproduct.hotelreservationapp.data.repository

import android.content.SharedPreferences
import android.util.Log
import com.example.dechproduct.hotelreservationapp.data.api.ReservationAPIService
import com.example.dechproduct.hotelreservationapp.data.model.booking.Booking
import com.example.dechproduct.hotelreservationapp.data.model.booking.BookingDTO
import com.example.dechproduct.hotelreservationapp.data.model.booking.BookingStatus
import com.example.dechproduct.hotelreservationapp.domain.repository.ReservationRepository
import com.example.dechproduct.hotelreservationapp.util.Resource
import javax.inject.Inject

class ReservationRepositoryImpl @Inject constructor(
    private val reservationAPI: ReservationAPIService,
    val sharedPreferences: SharedPreferences
) : ReservationRepository {

    override suspend fun add(booking: Booking): Resource<Booking> {
        return try {

            var response_msg = reservationAPI.postBooking(booking)

            Log.d("POST:", response_msg.toString())
            if (response_msg.isSuccessful)
                Resource.Success(booking)
            else
                throw Exception("Can't communicate with the server.")

        } catch (exception: Exception) {
            Log.d("ReserveRepositoryImpl", exception.toString())
            Resource.Failure(exception)
        }
    }

    override suspend fun edit(booking: Booking): Resource<Booking> {
        return try {

            var response = booking.bookingID?.let { reservationAPI.updateBooking(it, booking) }
            if (response!!.isSuccessful) {
                Resource.Success(booking)
            } else {
                throw Exception("Server denies request.")
            }

            Resource.Success(booking)
        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }

    override suspend fun remove(booking: Booking): Resource<Booking> {
        return try {

            var response = booking.bookingID?.let { reservationAPI.deleteBooking(it) }
            if (response!!.isSuccessful) {
                Resource.Success(booking)
            } else {
                throw Exception("Server denies request.")
            }

        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }

    override suspend fun getByID(keyword: String): Resource<Booking> {
        return try {
            var result_id = reservationAPI.getByID(keyword)
            Resource.Success(result_id.toBooking())
        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }

    override suspend fun searchByName(
        keyword: String,
        status: BookingStatus
    ): Resource<MutableList<Booking>> {
        return try {
            var results: MutableList<Booking> = mutableListOf<Booking>()
            var result_fname = reservationAPI.getByFirstName(keyword)
            var result_lname = reservationAPI.getByLastName(keyword)

            filterResult(result_fname, results, status)
            filterResult(result_lname, results, status)

            Resource.Success(results)

        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }

    //BookingID
    override suspend fun searchByID(
        keyword: String,
        status: BookingStatus
    ): Resource<MutableList<Booking>> {
        return try {
            var results: MutableList<Booking> = mutableListOf<Booking>()
            var result_id = reservationAPI.getByBookingID(keyword)

            filterResult(result_id, results, status)
            Resource.Success(results)

        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }

    //TODO: Implements search keyword e.g. #TODAY
    override suspend fun searchByDate(
        keyword: String,
        status: BookingStatus
    ): Resource<MutableList<Booking>> {
        return try {
            var results: MutableList<Booking> = mutableListOf<Booking>()
            var result_date = reservationAPI.getByBookingArrivalDate(keyword)

            filterResult(result_date, results, status)
            Resource.Success(results)

        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }

    //RoomID
    override suspend fun searchByRoomID(
        keyword: String,
        status: BookingStatus
    ): Resource<MutableList<Booking>> {
        return try {
            var results: MutableList<Booking> = mutableListOf<Booking>()
            var result_id = reservationAPI.getByBookingID(keyword)

            filterResult(result_id, results, status)
            Resource.Success(results)

        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }

    override suspend fun populate(status: BookingStatus): Resource<MutableList<Booking>> {
        return try {

            var results: MutableList<Booking> = mutableListOf<Booking>()

            var result_default = reservationAPI.getAll()
            filterResult(result_default, results, status)

            Resource.Success(results)
        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }

    private fun filterResult(
        s_results: List<BookingDTO>,
        results: MutableList<Booking>,
        status: BookingStatus = BookingStatus.NONE
    ) {
        for (result in s_results) {
            try {
                if (status == BookingStatus.NONE || status.internalCode == result.status) {
                    results.add(result.toBooking())
                }
            } catch (e: java.lang.Exception) {
            }
        }
    }
}
