package com.example.dechproduct.hotelreservationapp.di

import android.content.Context
import android.content.SharedPreferences
import com.example.dechproduct.hotelreservationapp.data.api.ReservationAPIService
import com.example.dechproduct.hotelreservationapp.data.api.UserAPIService
import com.example.dechproduct.hotelreservationapp.data.repository.UserRepositoryImpl
import com.example.dechproduct.hotelreservationapp.domain.repository.UserRepository
import com.example.dechproduct.hotelreservationapp.domain.usecase.UseCase
import com.example.dechproduct.hotelreservationapp.domain.usecase.login.LoginUseCase
import com.example.dechproduct.hotelreservationapp.util.Constants
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
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

    /*
    @Provides
    @Singleton
    fun provideRealTimeDatabase(): FirebaseDatabase {
        return FirebaseDatabase.getInstance(Constants.FIREBASE_DB_URL)
    }
    */

}