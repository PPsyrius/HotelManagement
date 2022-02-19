package com.example.dechproduct.hotelreservationapp.data.repository

import android.content.SharedPreferences
import android.util.Log
import com.example.dechproduct.hotelreservationapp.data.api.ReservationAPIService
import com.example.dechproduct.hotelreservationapp.data.model.Address
import com.example.dechproduct.hotelreservationapp.data.model.Booking
import com.example.dechproduct.hotelreservationapp.domain.repository.ReservationRepository
import com.example.dechproduct.hotelreservationapp.util.Constants
import com.example.dechproduct.hotelreservationapp.util.Resource
import com.google.firebase.database.*
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ReservationRepositoryImpl @Inject constructor(
    private val reservationAPI: ReservationAPIService,
    val sharedPreferences: SharedPreferences): ReservationRepository {

    override suspend fun addReservation(booking: Booking): Resource<Booking> {
        return try {

            val uid = System.currentTimeMillis()
            var response_msg = reservationAPI.postBooking(booking)

            Log.d("POST:", response_msg.toString())
            if(response_msg.isSuccessful)
                Resource.Success(booking)
            else
                throw Exception("Can't communicate with the server.")

        } catch (exception: Exception) {
            Log.d("ReserveRepositoryImpl", exception.toString())
            Resource.Failure(exception)
        }
    }

    override suspend fun searchReservation(keyword: String): Resource<MutableList<Booking>> {
        return try {

            var results: MutableList<Booking> = mutableListOf<Booking>()
            /*
            var test: String = ""

            bookingNode.orderByChild(Constants.BOOK_KEY_FNAME).equalTo(keyword).get()
                .await().children.map { item ->
                var booking: Booking = Booking(
                    item.child(Constants.BOOK_KEY_ID).getValue(String::class.java),
                    item.child(Constants.BOOK_KEY_FNAME).getValue(String::class.java),
                    item.child(Constants.BOOK_KEY_LNAME).getValue(String::class.java),
                    item.child(Constants.BOOK_KEY_PHONE).getValue(String::class.java),
                    Address(item.child(Constants.BOOK_KEY_ADDRESS).getValue(String::class.java)),
                    item.child(Constants.BOOK_KEY_SSN).getValue(String::class.java),
                    item.child(Constants.BOOK_KEY_DATE_IN).getValue(String::class.java),
                    item.child(Constants.BOOK_KEY_DATE_OUT).getValue(String::class.java),
                    item.child(Constants.BOOK_KEY_PAYMENT).getValue(String::class.java),
                    guestPass = null, guestRoom = null
                )

                results.add(booking)
                //Log.d("ReserveRepo",test)
            }

            bookingNode.orderByChild(Constants.BOOK_KEY_LNAME).equalTo(keyword).get()
                .await().children.map { item ->
                var booking: Booking = Booking(
                    item.child(Constants.BOOK_KEY_ID).getValue(String::class.java),
                    item.child(Constants.BOOK_KEY_FNAME).getValue(String::class.java),
                    item.child(Constants.BOOK_KEY_LNAME).getValue(String::class.java),
                    item.child(Constants.BOOK_KEY_PHONE).getValue(String::class.java),
                    Address(item.child(Constants.BOOK_KEY_ADDRESS).getValue(String::class.java)),
                    item.child(Constants.BOOK_KEY_SSN).getValue(String::class.java),
                    item.child(Constants.BOOK_KEY_DATE_IN).getValue(String::class.java),
                    item.child(Constants.BOOK_KEY_DATE_OUT).getValue(String::class.java),
                    item.child(Constants.BOOK_KEY_PAYMENT).getValue(String::class.java),
                    guestPass = null, guestRoom = null
                )

                results.add(booking)
                //Log.d("ReserveRepo",test)
            }
            */

            var result_fname = reservationAPI.getByFirstName(keyword)
            var result_lname = reservationAPI.getByLastName(keyword)

            //TODO: Partial search implementations?
            for(item in result_fname){
                results.add(item.toBooking())
            }
            for(item in result_lname) {
                results.add(item.toBooking())
            }

            Resource.Success(results)
        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }

    override suspend fun populateReservation(): Resource<MutableList<Booking>> {
        return try {

            var results: MutableList<Booking> = mutableListOf<Booking>()
            /*
            var test: String = ""

            bookingNode.orderByChild(Constants.BOOK_KEY_FNAME).get().await().children.map { item ->
                var booking: Booking = Booking(
                    item.child(Constants.BOOK_KEY_ID).getValue(String::class.java),
                    item.child(Constants.BOOK_KEY_FNAME).getValue(String::class.java),
                    item.child(Constants.BOOK_KEY_LNAME).getValue(String::class.java),
                    item.child(Constants.BOOK_KEY_PHONE).getValue(String::class.java),
                    Address(item.child(Constants.BOOK_KEY_ADDRESS).getValue(String::class.java)),
                    item.child(Constants.BOOK_KEY_SSN).getValue(String::class.java),
                    item.child(Constants.BOOK_KEY_DATE_IN).getValue(String::class.java),
                    item.child(Constants.BOOK_KEY_DATE_OUT).getValue(String::class.java),
                    item.child(Constants.BOOK_KEY_PAYMENT).getValue(String::class.java),
                    guestPass = null, guestRoom = null
                )

                results.add(booking)
                //Log.d("ReserveRepo",test)
            }
             */

            var result_default = reservationAPI.getAll()
            for(item in result_default){
                results.add(item.toBooking())
            }

            Resource.Success(results)
        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }

    override suspend fun editReservation(booking: Booking): Resource<Booking> {
        return try {

            var response = booking.bookingID?.let { reservationAPI.updateBooking(it,booking) }
            if(response!!.isSuccessful){
                Resource.Success(booking)
            }
            else{
                throw Exception("Server denies request.")
            }

            Resource.Success(booking)
        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }

    override suspend fun removeReservation(booking: Booking): Resource<Booking> {
        return try {

            var response = booking.bookingID?.let { reservationAPI.deleteBooking(it) }
            if(response!!.isSuccessful){
                Resource.Success(booking)
            }
            else{
                throw Exception("Server denies request.")
            }

        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }

}
