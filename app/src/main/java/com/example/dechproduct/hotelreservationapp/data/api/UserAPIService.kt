package com.example.dechproduct.hotelreservationapp.data.api

import com.example.dechproduct.hotelreservationapp.data.model.StaffDTO
import com.example.dechproduct.hotelreservationapp.data.model.unused.APIResponse
import com.example.dechproduct.hotelreservationapp.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface UserAPIService {

    @GET(Constants.API_USER_INDEX_URL)
    suspend fun getByUserName(
        @Query("userName")
        user_name:String,
    ): StaffDTO

}