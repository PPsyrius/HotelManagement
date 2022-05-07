package com.example.dechproduct.hotelreservationapp.data.api

import com.example.dechproduct.hotelreservationapp.data.model.booking.BookingDTO
import com.example.dechproduct.hotelreservationapp.data.model.employee.AccessDTO
import com.example.dechproduct.hotelreservationapp.util.Constants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserAPIService {

    @GET(Constants.API_ACCESS_INDEX_URL)
    suspend fun getByUserName(
        @Query(Constants.API_ACCESS_KEY_USERNAME)
        username:String,
    ): List<AccessDTO>

    @GET(Constants.API_ACCESS_INDEX_URL + "{id}")
    suspend fun getByUserID(
        @Path("id")
        id:String,
    ): List<AccessDTO>

}