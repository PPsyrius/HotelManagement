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
        first_name:String,
    ): List<BookingDTO>

    @GET(Constants.API_BOOK_INDEX_URL)
    suspend fun getByLastName(
        @Query(Constants.API_BOOK_KEY_LNAME)
        last_name:String,
    ): List<BookingDTO>

    @POST(Constants.API_BOOK_INDEX_URL)
    suspend fun postBooking(
        @Body booking: Booking,
    ): Response<ResponseBody>

    //For placeholder only
    @GET("/foo/{id}/bar")
    suspend fun test(
        @Path("id")
        id:String,
    ): Response<APIResponse>
}