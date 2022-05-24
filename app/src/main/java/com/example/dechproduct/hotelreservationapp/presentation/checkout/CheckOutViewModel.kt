package com.example.dechproduct.hotelreservationapp.presentation.checkout

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
class CheckOutViewModel @Inject constructor(private val useCase: UseCase) : ViewModel() {

    var reserver = MutableLiveData<Resource<MutableList<Booking>>>()
    var resolveReservation = MutableLiveData<Resource<Booking>>()

    lateinit var result: MutableList<Booking>

    var dateFormat = SimpleDateFormat("dd-MM-yyyy")

    fun searchReserve(keyword: String) {
        viewModelScope.launch {
            val reservation = useCase.searchReserveByNamedDepartUseCase(
                keyword,
                dateFormat.format(Date()),
                mutableListOf<BookingStatus>(BookingStatus.CHECK_IN)
            )
            reserver.postValue(reservation)
        }
    }

    fun populateReserve() {
        viewModelScope.launch {
            val reservations =
                useCase.searchReserveByDepartUseCase(
                    dateFormat.format(Date()),
                    mutableListOf<BookingStatus>(BookingStatus.CHECK_IN)
                )
            reserver.postValue(reservations)
        }
    }

    fun checkOutReserved(booking: Booking) {
        viewModelScope.launch {
            val response = useCase.checkOutGuestUseCase(booking)
            resolveReservation.postValue(response)
        }
    }
}