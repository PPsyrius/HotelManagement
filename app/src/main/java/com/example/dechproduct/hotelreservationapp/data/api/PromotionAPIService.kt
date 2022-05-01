package com.example.dechproduct.hotelreservationapp.data.api

import com.example.dechproduct.hotelreservationapp.data.model.BookingDTO
import com.example.dechproduct.hotelreservationapp.data.model.PromotionDTO
import com.example.dechproduct.hotelreservationapp.data.model.StaffDTO
import com.example.dechproduct.hotelreservationapp.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface PromotionAPIService {

    @GET(Constants.API_INFO_INDEX_URL)
    suspend fun getAllPromotion(): List<PromotionDTO>

    @GET(Constants.API_INFO_INDEX_URL)
    suspend fun getByPartner(
        @Query(Constants.API_INFO_KEY_PARTNER + Constants.JSON_SERVER_FILTER)
        keyword: String,
    ): List<PromotionDTO>

}