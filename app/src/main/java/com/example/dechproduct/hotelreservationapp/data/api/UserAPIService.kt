package com.example.dechproduct.hotelreservationapp.data.api

import com.example.dechproduct.hotelreservationapp.data.model.StaffDTO
import com.example.dechproduct.hotelreservationapp.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface UserAPIService {

    @GET(Constants.API_USER_INDEX_URL)
    suspend fun getByUserName(
        @Query(Constants.API_USER_KEY_USERNAME)
        user_name:String,
    ): List<StaffDTO>

}