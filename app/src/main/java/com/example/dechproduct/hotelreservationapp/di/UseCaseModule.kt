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
    fun provideUseCase(userRepository: UserRepository,
                       reservationRepository: ReservationRepository,
                       roomRepository: RoomRepository,
                       informationRepository: InformationRepository
    ): UseCase {
        return UseCase(
            LoginUseCase(userRepository),
            LogoutUseCase(userRepository),

            CheckInFromReservationUseCase(roomRepository, EditReserveUseCase(reservationRepository)),
            CheckInFromWalkInUseCase(roomRepository, AddReserveUseCase(reservationRepository)),

            CheckOutGuestUseCase(reservationRepository, roomRepository),
            ExtendStayUseCase(reservationRepository, roomRepository),
            SearchGuestUseCase(reservationRepository, roomRepository),

            GetReserveByIDUseCase(reservationRepository),

            ShowPromotionUseCase(informationRepository),

            AddReserveUseCase(reservationRepository),
            EditReserveUseCase(reservationRepository),
            PopulateReserveUseCase(reservationRepository),
            RemoveReserveUseCase(reservationRepository),
            SearchReserveByIDUseCase(reservationRepository),
            SearchReserveByNameUseCase(reservationRepository),

            MarkRoomUseCase(roomRepository),
            SearchRoomUseCase(roomRepository),
        )
    }
}