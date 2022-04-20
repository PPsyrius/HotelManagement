package com.example.dechproduct.hotelreservationapp.data.api

import com.example.dechproduct.hotelreservationapp.data.model.StaffDTO
import com.example.dechproduct.hotelreservationapp.data.model.unused.APIResponse
import com.example.dechproduct.hotelreservationapp.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface PromotionAPIService {

    //TODO:Promotion DTO
    @GET(Constants.API_INFO_INDEX_URL)
    suspend fun getAllPromotion(): List<String>

}