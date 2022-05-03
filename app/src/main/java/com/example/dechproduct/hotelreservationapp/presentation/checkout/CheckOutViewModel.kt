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
import javax.inject.Inject


@HiltViewModel
class CheckOutViewModel @Inject constructor(private val useCase: UseCase): ViewModel(){

    var reserver = MutableLiveData<Resource<MutableList<Booking>>>()

    suspend fun searchReserve(keyword:String){
        viewModelScope.launch {
            val reservation = useCase.searchReserveByNameUseCase(keyword, arg = BookingStatus.CHECK_IN)
            reserver.postValue(reservation)
        }
    }
    //Only searchReserve() update observer for now.

    suspend fun populateReserve(){
        viewModelScope.launch {
            val reservation = useCase.populateReserveUseCase(arg = BookingStatus.CHECK_IN)
            reserver.postValue(reservation)
        }
    }

}