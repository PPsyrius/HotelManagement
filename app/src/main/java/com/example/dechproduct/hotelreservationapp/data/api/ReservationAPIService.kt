package com.example.dechproduct.hotelreservationapp.data.api

import com.example.dechproduct.hotelreservationapp.data.model.Booking
import com.example.dechproduct.hotelreservationapp.data.model.BookingDTO
import com.example.dechproduct.hotelreservationapp.data.model.unused.APIResponse
import com.example.dechproduct.hotelreservationapp.util.Constants
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface ReservationAPIService {

    @GET(Constants.API_BOOK_INDEX_URL)
    suspend fun getByFirstName(
        @Query(Constants.API_BOOK_KEY_FNAME)
        first_name: String,
    ): List<BookingDTO>

    @GET(Constants.API_BOOK_INDEX_URL)
    suspend fun getByLastName(
        @Query(Constants.API_BOOK_KEY_LNAME)
        last_name: String,
    ): List<BookingDTO>

    @GET(Constants.API_BOOK_INDEX_URL)
    suspend fun getByBookingID(
        @Query(Constants.API_BOOK_KEY_ID)
        id: String,
    ): List<BookingDTO>

    @GET(Constants.API_BOOK_INDEX_URL)
    suspend fun getByBookingArrivalDate(
        @Query(Constants.API_BOOK_KEY_DATE_IN)
        id: String,
    ): List<BookingDTO>

    @GET(Constants.API_BOOK_INDEX_URL + "/{id}")
    suspend fun getByID(
        @Path("id")
        id: String,
    ): BookingDTO

    @GET(Constants.API_BOOK_INDEX_URL)
    suspend fun getAll(): List<BookingDTO>

    @POST(Constants.API_BOOK_INDEX_URL)
    suspend fun postBooking(
        @Body booking: Booking,
    ): Response<ResponseBody>

    @PUT(Constants.API_BOOK_INDEX_URL + "/{id}")
    suspend fun putBooking(
        @Path("id")
        id: String,
        @Body booking: Booking
    ): Response<ResponseBody>

    @PATCH(Constants.API_BOOK_INDEX_URL + "/{id}")
    suspend fun updateBooking(
        @Path("id")
        id: String,
        @Body booking: Booking
    ): Response<ResponseBody>

    @DELETE(Constants.API_BOOK_INDEX_URL)
    suspend fun deleteBooking(
        @Query(Constants.API_BOOK_KEY_ID)
        id: String,
    ): Response<ResponseBody>

}