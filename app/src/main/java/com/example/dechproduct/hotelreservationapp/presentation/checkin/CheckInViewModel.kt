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
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class CheckInViewModel @Inject constructor(private val useCase: UseCase) : ViewModel() {

    var reserver = MutableLiveData<Resource<MutableList<Booking>>>()
    lateinit var result: MutableList<Booking>

    var refreshCall = MutableLiveData<BookingStatus>()
    var dateFormat = SimpleDateFormat("dd-MM-yyyy")

    fun searchReserve(keyword: String) {
        viewModelScope.launch {
            val reservations =
                useCase.searchReserveByNamedArrivalUseCase(
                    keyword,
                    dateFormat.format(Date()),
                    mutableListOf<BookingStatus>(BookingStatus.RESERVED)
                )
            reserver.postValue(reservations)
        }
    }

    fun populateReserve() {
        viewModelScope.launch {
            val reservations =
                useCase.searchReserveByArrivalUseCase(
                    dateFormat.format(Date()),
                    mutableListOf<BookingStatus>(BookingStatus.RESERVED)
                )
            reserver.postValue(reservations)
        }
    }

}