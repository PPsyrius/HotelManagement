package com.example.dechproduct.hotelreservationapp.di

import com.example.dechproduct.hotelreservationapp.domain.repository.InformationRepository
import com.example.dechproduct.hotelreservationapp.domain.repository.ReservationRepository
import com.example.dechproduct.hotelreservationapp.domain.repository.RoomRepository
import com.example.dechproduct.hotelreservationapp.domain.repository.UserRepository
import com.example.dechproduct.hotelreservationapp.domain.usecase.UseCase
import com.example.dechproduct.hotelreservationapp.domain.usecase.checkin.*
import com.example.dechproduct.hotelreservationapp.domain.usecase.checkout.*
import com.example.dechproduct.hotelreservationapp.domain.usecase.information.*
import com.example.dechproduct.hotelreservationapp.domain.usecase.login.*
import com.example.dechproduct.hotelreservationapp.domain.usecase.reservation.*
import com.example.dechproduct.hotelreservationapp.domain.usecase.room.*
import com.example.dechproduct.hotelreservationapp.domain.usecase.utility.*

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideUseCase(userRepository: UserRepository,
                       reservationRepository: ReservationRepository,
                       roomRepository: RoomRepository,
                       informationRepository: InformationRepository
    ): UseCase {
        return UseCase(
            CheckInFromReservationUseCase(reservationRepository, roomRepository),
            CheckInFromWalkInUseCase(reservationRepository, roomRepository),

            CheckOutGuestUseCase(reservationRepository, roomRepository),
            ExtendStayUseCase(reservationRepository, roomRepository),
            SearchGuestUseCase(reservationRepository, roomRepository),

            ShowPromotionUseCase(informationRepository),

            LoginUseCase(userRepository),

            AddReserveUseCase(reservationRepository),
            SearchReserveUseCase(reservationRepository),
            PopulateReserveUseCase(reservationRepository),
            EditReserveUseCase(reservationRepository),
            RemoveReserveUseCase(reservationRepository),

            MarkRoomUseCase(roomRepository),
            SearchRoomUseCase(roomRepository),

            LogoutUseCase(userRepository),
        )
    }
}