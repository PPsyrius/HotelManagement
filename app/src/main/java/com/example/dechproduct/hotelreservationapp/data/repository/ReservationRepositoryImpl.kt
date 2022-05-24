package com.example.dechproduct.hotelreservationapp.data.repository

import android.content.SharedPreferences
import android.util.Log
import com.example.dechproduct.hotelreservationapp.data.api.ReservationAPIService
import com.example.dechproduct.hotelreservationapp.data.model.booking.Booking
import com.example.dechproduct.hotelreservationapp.data.model.booking.BookingDTO
import com.example.dechproduct.hotelreservationapp.data.model.booking.BookingStatus
import com.example.dechproduct.hotelreservationapp.data.model.room.RoomStatus
import com.example.dechproduct.hotelreservationapp.domain.repository.ReservationRepository
import com.example.dechproduct.hotelreservationapp.util.Resource
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class ReservationRepositoryImpl @Inject constructor(
    private val reservationAPI: ReservationAPIService,
    val sharedPreferences: SharedPreferences
) : ReservationRepository {

    private var dateFormat = SimpleDateFormat("dd-MM-yyyy")

    override suspend fun add(booking: Booking): Resource<Booking> {
        return try {
            var response_msg = reservationAPI.postBooking(booking.toBookingDTO())

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
            var response =
                booking.bookingID?.let { reservationAPI.updateBooking(it, booking.toBookingDTO()) }
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
        status: List<BookingStatus>
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
        status: List<BookingStatus>
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

    override suspend fun searchByArrivalDate(
        keyword: String,
        status: List<BookingStatus>
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

    override suspend fun searchByDepartDate(
        keyword: String,
        status: List<BookingStatus>
    ): Resource<MutableList<Booking>> {
        return try {
            var results: MutableList<Booking> = mutableListOf<Booking>()
            var result_date = reservationAPI.getByBookingDepartDate(keyword)

            filterResult(result_date, results, status)
            Resource.Success(results)

        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }

    //RoomID
    override suspend fun searchByRoomID(
        keyword: String,
        status: List<BookingStatus>
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

    override suspend fun searchByNamedArrivalDate(
        name: String,
        date: String,
        status: List<BookingStatus>
    ): Resource<MutableList<Booking>> {
        return try {
            var results: MutableList<Booking> = mutableListOf<Booking>()
            var result_fname = reservationAPI.getByFirstName(name)
            var result_lname = reservationAPI.getByLastName(name)

            filterResult(result_fname, results, status)
            filterResult(result_lname, results, status)
            filterDate(date, results, true)

            Resource.Success(results)

        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }

    override suspend fun searchByNamedDepartDate(
        name: String,
        date: String,
        status: List<BookingStatus>
    ): Resource<MutableList<Booking>> {
        return try {
            var results: MutableList<Booking> = mutableListOf<Booking>()
            var result_fname = reservationAPI.getByFirstName(name)
            var result_lname = reservationAPI.getByLastName(name)

            filterResult(result_fname, results, status)
            filterResult(result_lname, results, status)
            filterDate(date, results, false)

            Resource.Success(results)

        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }

    override suspend fun populate(status: List<BookingStatus>): Resource<MutableList<Booking>> {
        return try {

            var results: MutableList<Booking> = mutableListOf<Booking>()

            var result_default = reservationAPI.getAll()
            filterResult(result_default, results, status)

            Resource.Success(results)
        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }

    private fun filterDate(date: String, results: MutableList<Booking>, isArrival: Boolean) {
        val target = dateFormat.parse(date)
        for(result in results){
            if(isArrival && result.arrivalDate != target){
                results.remove(result)
            }
            else if(!isArrival && result.departDate != target){
                results.remove(result)
            }
        }
    }

    private fun filterResult(
        s_results: List<BookingDTO>,
        results: MutableList<Booking>,
        status: List<BookingStatus> = mutableListOf<BookingStatus>()
    ) {
        for (result in s_results) {
            try {
                if (status.isEmpty() || status.contains(BookingStatus.unpack(result.status.toString()))) {
                    results.add(result.toBooking())
                }
            } catch (e: Exception) {
                Log.d("ERR:", e.toString())
            }
        }
    }
}
