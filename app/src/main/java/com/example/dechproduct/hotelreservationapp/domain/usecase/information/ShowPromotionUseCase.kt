package com.example.dechproduct.hotelreservationapp.domain.usecase.information

import com.example.dechproduct.hotelreservationapp.data.model.Booking
import com.example.dechproduct.hotelreservationapp.domain.repository.InformationRepository
import com.example.dechproduct.hotelreservationapp.domain.repository.ReservationRepository
import com.example.dechproduct.hotelreservationapp.domain.repository.RoomRepository
import com.example.dechproduct.hotelreservationapp.util.Resource
import javax.inject.Inject

class ShowPromotionUseCase @Inject constructor(
    private val informationRepository: InformationRepository) {
    //suspend operator fun invoke(keyword: String): Resource<MutableList<Booking>> = reservationRepository.searchReservation(keyword)
}