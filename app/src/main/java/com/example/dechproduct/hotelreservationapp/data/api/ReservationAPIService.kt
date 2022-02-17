package com.example.dechproduct.hotelreservationapp.data.api

import com.example.dechproduct.hotelreservationapp.data.model.BookingDTO
import com.example.dechproduct.hotelreservationapp.data.model.unused.APIResponse
import com.example.dechproduct.hotelreservationapp.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ReservationAPIService {

    @GET(Constants.API_BOOK_INDEX_URL)
    suspend fun getByFirstName(
        @Query("firstName")
        first_name:String,
    ): List<BookingDTO>

    @GET(Constants.API_BOOK_INDEX_URL)
    suspend fun getByLastName(
        @Query("lastName")
        last_name:String,
    ): List<BookingDTO>

    //For placeholder only
    @GET("/foo/{id}/bar")
    suspend fun test(
        @Path("id")
        id:String,
    ): Response<APIResponse>
}