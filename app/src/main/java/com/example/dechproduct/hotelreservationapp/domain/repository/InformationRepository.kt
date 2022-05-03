package com.example.dechproduct.hotelreservationapp.domain.repository

import com.example.dechproduct.hotelreservationapp.data.model.promotion.Promotion
import com.example.dechproduct.hotelreservationapp.util.Resource

interface InformationRepository {
    suspend fun getAllPromotion(): Resource<MutableList<Promotion>>
    suspend fun searchPromotion(partners: List<String>): Resource<MutableList<Promotion>>
}