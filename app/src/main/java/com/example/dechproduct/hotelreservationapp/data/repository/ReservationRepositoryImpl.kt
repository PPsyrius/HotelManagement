package com.example.dechproduct.hotelreservationapp.data.repository

import android.content.SharedPreferences
import android.util.Log
import com.example.dechproduct.hotelreservationapp.data.model.Address
import com.example.dechproduct.hotelreservationapp.data.model.Booking
import com.example.dechproduct.hotelreservationapp.domain.repository.ReservationRepository
import com.example.dechproduct.hotelreservationapp.util.Constants
import com.example.dechproduct.hotelreservationapp.util.Resource
import com.google.firebase.database.*
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ReservationRepositoryImpl @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase,
    val sharedPreferences: SharedPreferences): ReservationRepository {

    override suspend fun addReservation(booking: Booking): Resource<Booking> {
        return try {
            //TODO: Migrate Database
            val bookingNode = firebaseDatabase.getReference(Constants.BOOK_DB_NODE)
            val uid = System.currentTimeMillis()

            bookingNode.child(uid.toString()).child(Constants.BOOK_KEY_ID)
                .setValue(uid)
            bookingNode.child(uid.toString()).child(Constants.BOOK_KEY_FNAME)
                .setValue(booking.firstName)
            bookingNode.child(uid.toString()).child(Constants.BOOK_KEY_LNAME)
                .setValue(booking.lastName)
            bookingNode.child(uid.toString()).child(Constants.BOOK_KEY_PHONE)
                .setValue(booking.phoneNumber)
            bookingNode.child(uid.toString()).child(Constants.BOOK_KEY_ADDRESS)
                .setValue(booking.address)
            bookingNode.child(uid.toString()).child(Constants.BOOK_KEY_PAYMENT)
                .setValue(booking.paymentType)
            bookingNode.child(uid.toString()).child(Constants.BOOK_KEY_SSN)
                .setValue(booking.verificationID)
            bookingNode.child(uid.toString()).child(Constants.BOOK_KEY_DATE_IN)
                .setValue(booking.arrivalDate)
            bookingNode.child(uid.toString()).child(Constants.BOOK_KEY_DATE_OUT)
                .setValue(booking.departDate)

            Resource.Success(booking)
        } catch (exception: Exception) {
            Log.d("ReserveRepositoryImpl", exception.toString())
            Resource.Failure(exception)
        }
    }

    override suspend fun searchReservation(keyword: String): Resource<MutableList<Booking>> {
        return try {
            val bookingNode = firebaseDatabase.reference.child(Constants.BOOK_DB_NODE)
            var results: MutableList<Booking> = mutableListOf<Booking>()

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

            Resource.Success(results)
        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }

    override suspend fun populateReservation(): Resource<MutableList<Booking>> {
        return try {
            val bookingNode = firebaseDatabase.reference.child(Constants.BOOK_DB_NODE)
            var results: MutableList<Booking> = mutableListOf<Booking>()

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
            Resource.Success(results)
        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }

    override suspend fun editReservation(booking: Booking): Resource<Booking> {
        return try {
            Resource.Success(Booking(
                address = null, guestPass = null, guestRoom = null,
            ))
        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }

    override suspend fun removeReservation(booking: Booking): Resource<Booking> {
        return try {
            Resource.Success(Booking(
                address = null, guestPass = null, guestRoom = null,
            ))
        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }

}
