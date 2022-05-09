package com.example.dechproduct.hotelreservationapp.presentation.checkin

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
class CheckInViewModel @Inject constructor(private val useCase: UseCase) : ViewModel() {

    var reserver = MutableLiveData<Resource<MutableList<Booking>>>()
    lateinit var result: MutableList<Booking>

    suspend fun searchReserve(keyword: String) {
        viewModelScope.launch {
            val reservations =
                useCase.searchReserveByNameUseCase(keyword, mutableListOf<BookingStatus>(BookingStatus.RESERVED))
            reserver.postValue(reservations)
        }
    }

    suspend fun populateReserve() {
        viewModelScope.launch {
            val reservations = useCase.populateReserveUseCase(mutableListOf<BookingStatus>(BookingStatus.RESERVED))
            reserver.postValue(reservations)
        }
    }

}