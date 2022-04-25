package com.example.dechproduct.hotelreservationapp.presentation.checkin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dechproduct.hotelreservationapp.data.model.Booking
import com.example.dechproduct.hotelreservationapp.data.model.utility.booking.GuestStatus
import com.example.dechproduct.hotelreservationapp.domain.usecase.UseCase
import com.example.dechproduct.hotelreservationapp.util.Constants
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
                useCase.searchReserveByNameUseCase(keyword, arg = GuestStatus.RESERVED)
            reserver.postValue(reservations)
        }
    }

    suspend fun populateReserve() {
        viewModelScope.launch {
            val reservations = useCase.populateReserveUseCase(arg = GuestStatus.RESERVED)
            reserver.postValue(reservations)
        }
    }

}