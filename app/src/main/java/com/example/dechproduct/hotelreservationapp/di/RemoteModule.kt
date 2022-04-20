package com.example.dechproduct.hotelreservationapp.di

import com.example.dechproduct.hotelreservationapp.data.api.PromotionAPIService
import com.example.dechproduct.hotelreservationapp.data.api.ReservationAPIService
import com.example.dechproduct.hotelreservationapp.data.api.RoomAPIService
import com.example.dechproduct.hotelreservationapp.data.api.UserAPIService
import com.example.dechproduct.hotelreservationapp.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    @Singleton
    fun provideGuestbookAPI(): ReservationAPIService {
        return Retrofit.Builder()
            .baseUrl(Constants.API_BOOK_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ReservationAPIService::class.java)
    }

    @Provides
    @Singleton
    fun provideUserAPI(): UserAPIService {
        return Retrofit.Builder()
            .baseUrl(Constants.API_USER_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserAPIService::class.java)
    }

    @Provides
    @Singleton
    fun provideRoomAPI(): RoomAPIService {
        return Retrofit.Builder()
            .baseUrl(Constants.API_ROOM_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RoomAPIService::class.java)
    }

    @Provides
    @Singleton
    fun providePromotionAPI(): PromotionAPIService {
        return Retrofit.Builder()
            .baseUrl(Constants.API_INFO_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PromotionAPIService::class.java)
    }
}