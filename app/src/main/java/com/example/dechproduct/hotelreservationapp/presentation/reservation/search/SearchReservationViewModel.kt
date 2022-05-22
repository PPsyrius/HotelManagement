package com.example.dechproduct.hotelreservationapp.presentation.reservation.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dechproduct.hotelreservationapp.data.model.booking.Booking
import com.example.dechproduct.hotelreservationapp.data.model.booking.BookingStatus
import com.example.dechproduct.hotelreservationapp.domain.usecase.UseCase
import com.example.dechproduct.hotelreservationapp.util.Filter
import com.example.dechproduct.hotelreservationapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SearchReservationViewModel @Inject constructor(private val useCase: UseCase) : ViewModel() {

    var getter = MutableLiveData<Resource<MutableList<Booking>>>()
    var setter = MutableLiveData<Resource<Booking>>()
    lateinit var reservation: MutableList<Booking>

    fun queryHandle(query: String): Boolean {
        //var keywords = query.split(" ")
        var dateFormat = SimpleDateFormat("dd-MM-yyyy")
        var rex = "\\d{2}-\\d{2}-\\d{4}".toRegex()

        when {
            query == "" -> populateReservation()

            query.contains(Filter.TODAY) -> {
                searchReservationByDate(dateFormat.format(Date()))
            }

            query.contains(Filter.TOMORROW) -> {
                searchReservationByDate(dateFormat.format(Date().time + (1000 * 60 * 60 * 24)))
            }

            rex.containsMatchIn(query) -> {
                rex.find(query)?.let { searchReservationByDate(it.value) }
            }

            else -> searchReservationByName(query.capitalize())
        }
        return false
    }

    private fun searchReservationByDate(keyword: String = "") {
        viewModelScope.launch {
            val reservation =
                useCase.searchReserveByArrivalUseCase(
                    keyword,
                    mutableListOf<BookingStatus>(BookingStatus.RESERVED)
                )
            getter.postValue(reservation)
        }
    }

    private fun searchReservationByName(keyword: String) {
        viewModelScope.launch {
            val reservation =
                useCase.searchReserveByNameUseCase(
                    keyword,
                    mutableListOf<BookingStatus>(BookingStatus.RESERVED)
                )
            getter.postValue(reservation)
        }
    }

    private fun populateReservation() {
        viewModelScope.launch {
            val reservation =
                useCase.populateReserveUseCase(mutableListOf<BookingStatus>(BookingStatus.RESERVED))
            getter.postValue(reservation)
        }
    }

    fun removeReservation(booking: Booking) {
        viewModelScope.launch {
            val reservation = useCase.cancelReserveUseCase(booking)
            setter.postValue(reservation)
        }
    }

}