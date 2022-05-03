package com.example.dechproduct.hotelreservationapp.data.repository

import android.content.SharedPreferences
import android.util.Log
import com.example.dechproduct.hotelreservationapp.data.api.PromotionAPIService
import com.example.dechproduct.hotelreservationapp.data.model.Promotion
import com.example.dechproduct.hotelreservationapp.data.model.PromotionDTO
import com.example.dechproduct.hotelreservationapp.domain.repository.InformationRepository
import com.example.dechproduct.hotelreservationapp.util.Resource
import javax.inject.Inject

class InformationRepositoryImpl @Inject constructor(
    private val promotionAPI: PromotionAPIService,
    val sharedPreferences: SharedPreferences
) : InformationRepository {

    override suspend fun getAllPromotion(): Resource<MutableList<Promotion>> {
        return try {
            var results: MutableList<Promotion> = mutableListOf<Promotion>()
            var promotions = promotionAPI.getAllPromotion()
            filterResult(promotions, results)

            Resource.Success(results)

        } catch (exception: Exception) {
            Log.d("InfoRepositoryImpl", exception.toString())
            Resource.Failure(exception)
        }
    }

    override suspend fun searchPromotion(partners: List<String>): Resource<MutableList<Promotion>> {
        return try {
            var results: MutableList<Promotion> = mutableListOf<Promotion>()
            var query: String = ""
            for (partner in partners) {
                query += "$partner,"
            }
            query.dropLast(1)

            var promotions = promotionAPI.getByPartner(query)
            filterResult(promotions, results)

            Resource.Success(results)

        } catch (exception: Exception) {
            Log.d("InfoRepositoryImpl", exception.toString())
            Resource.Failure(exception)
        }
    }

    private fun filterResult(promotions: List<PromotionDTO>, results: MutableList<Promotion>) {
        for (promotion in promotions) {
            try {
                results.add(promotion.toPromotion())
            } catch (e: Exception) {
            }
        }
    }
}
