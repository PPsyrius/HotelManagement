package com.example.dechproduct.hotelreservationapp.data.api

import com.example.dechproduct.hotelreservationapp.data.model.promotion.PromotionDTO
import com.example.dechproduct.hotelreservationapp.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface PromotionAPIService {

    @GET(Constants.API_PROMOTION_INDEX_URL)
    suspend fun getAllPromotion(): List<PromotionDTO>

    @GET(Constants.API_PROMOTION_INDEX_URL)
    suspend fun getByPartner(
        @Query(Constants.API_PROMOTION_KEY_PARTNER + Constants.JSON_SERVER_FILTER)
        keyword: String,
    ): List<PromotionDTO>

}