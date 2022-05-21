package com.example.dechproduct.hotelreservationapp.presentation.reservation.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dechproduct.hotelreservationapp.data.model.booking.Booking
import com.example.dechproduct.hotelreservationapp.data.model.booking.BookingStatus
import com.example.dechproduct.hotelreservationapp.domain.usecase.UseCase
import com.example.dechproduct.hotelreservationapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchReservationViewModel @Inject constructor(private val useCase: UseCase) : ViewModel() {

    var getter = MutableLiveData<Resource<MutableList<Booking>>>()
    var setter = MutableLiveData<Resource<Booking>>()
    lateinit var reservation: MutableList<Booking>

    fun searchReservation(keyword: String) {
        viewModelScope.launch {
            val reservation =
                useCase.searchReserveByNameUseCase(keyword, mutableListOf<BookingStatus>(BookingStatus.RESERVED))
            getter.postValue(reservation)
        }
    }

    fun populateReservation() {
        viewModelScope.launch {
            val reservation = useCase.populateReserveUseCase(mutableListOf<BookingStatus>(BookingStatus.RESERVED))
            getter.postValue(reservation)
        }
    }

    fun removeReservation(booking: Booking){
        viewModelScope.launch {
            val reservation = useCase.cancelReserveUseCase(booking)
            setter.postValue(reservation)
        }
    }

}