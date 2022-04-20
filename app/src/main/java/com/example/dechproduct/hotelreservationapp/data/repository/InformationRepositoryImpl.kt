package com.example.dechproduct.hotelreservationapp.data.repository

import android.content.SharedPreferences
import android.util.Log
import com.example.dechproduct.hotelreservationapp.data.api.PromotionAPIService
import com.example.dechproduct.hotelreservationapp.data.api.ReservationAPIService
import com.example.dechproduct.hotelreservationapp.data.model.Address
import com.example.dechproduct.hotelreservationapp.data.model.Booking
import com.example.dechproduct.hotelreservationapp.domain.repository.InformationRepository
import com.example.dechproduct.hotelreservationapp.domain.repository.ReservationRepository
import com.example.dechproduct.hotelreservationapp.util.Constants
import com.example.dechproduct.hotelreservationapp.util.Resource
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class InformationRepositoryImpl @Inject constructor(
    private val promotionAPI: PromotionAPIService,
    val sharedPreferences: SharedPreferences): InformationRepository {

    override suspend fun getPromotion(): Resource<String> {
        return try {
            val uid = System.currentTimeMillis()
            var response_msg = promotionAPI.getAllPromotion()

            Log.d("POST:", response_msg.toString())
            Resource.Success("Success")

        } catch (exception: Exception) {
            Log.d("InfoRepositoryImpl", exception.toString())
            Resource.Failure(exception)
        }
    }
}
