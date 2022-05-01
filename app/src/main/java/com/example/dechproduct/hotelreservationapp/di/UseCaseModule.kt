package com.example.dechproduct.hotelreservationapp.di

import com.example.dechproduct.hotelreservationapp.domain.repository.InformationRepository
import com.example.dechproduct.hotelreservationapp.domain.repository.ReservationRepository
import com.example.dechproduct.hotelreservationapp.domain.repository.RoomRepository
import com.example.dechproduct.hotelreservationapp.domain.repository.UserRepository
import com.example.dechproduct.hotelreservationapp.domain.usecase.UseCase
import com.example.dechproduct.hotelreservationapp.domain.usecase.checkin.*
import com.example.dechproduct.hotelreservationapp.domain.usecase.checkout.*
import com.example.dechproduct.hotelreservationapp.domain.usecase.information.*
import com.example.dechproduct.hotelreservationapp.domain.usecase.auth.*
import com.example.dechproduct.hotelreservationapp.domain.usecase.com.GetReserveByIDUseCase
import com.example.dechproduct.hotelreservationapp.domain.usecase.reservation.*
import com.example.dechproduct.hotelreservationapp.domain.usecase.room.*

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideUseCase(
        userRepository: UserRepository,
        reservationRepository: ReservationRepository,
        roomRepository: RoomRepository,
        informationRepository: InformationRepository
    ): UseCase {
        return UseCase(
            LoginUseCase(userRepository),
            LogoutUseCase(userRepository),

            CheckInGuestUseCase(
                roomRepository,
                EditReserveUseCase(reservationRepository)
            ),

            CheckOutGuestUseCase(
                roomRepository,
                EditReserveUseCase(reservationRepository),
                EditRoomUseCase(roomRepository)
            ),
            ExtendStayUseCase(
                roomRepository,
                EditReserveUseCase(reservationRepository),
                EditRoomUseCase(roomRepository)
            ),
            SearchGuestUseCase(roomRepository, reservationRepository),

            GetReserveByIDUseCase(reservationRepository),

            ShowPromotionUseCase(informationRepository),

            AddReserveUseCase(reservationRepository),
            EditReserveUseCase(reservationRepository),
            PopulateReserveUseCase(reservationRepository),
            RemoveReserveUseCase(reservationRepository),
            SearchReserveByIDUseCase(reservationRepository),
            SearchReserveByNameUseCase(reservationRepository),

            EditRoomUseCase(roomRepository),
            MarkRoomUseCase(EditRoomUseCase(roomRepository)),
            SearchRoomUseCase(roomRepository),
        )
    }
}