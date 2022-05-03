package com.example.dechproduct.hotelreservationapp.domain.usecase.information

import com.example.dechproduct.hotelreservationapp.domain.repository.InformationRepository
import javax.inject.Inject

class ShowPromotionUseCase @Inject constructor(
    private val informationRepository: InformationRepository) {
    //suspend operator fun invoke(keyword: String): Resource<MutableList<Booking>> = reservationRepository.searchReservation(keyword)
}