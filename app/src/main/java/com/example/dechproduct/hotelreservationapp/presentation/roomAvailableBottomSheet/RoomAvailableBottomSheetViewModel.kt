package com.example.dechproduct.hotelreservationapp.presentation.roomAvailableBottomSheet

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dechproduct.hotelreservationapp.data.model.Booking
import com.example.dechproduct.hotelreservationapp.domain.usecase.UseCase
import com.example.dechproduct.hotelreservationapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomAvailableBottomSheetViewModel @Inject constructor(private val useCase: UseCase): ViewModel(){

    var reserver = MutableLiveData<Resource<MutableList<Booking>>>()

    suspend fun searchReserve(keyword:String){
        viewModelScope.launch {
            val reservation = useCase.searchReserveByNameUseCase(keyword)
            reserver.postValue(reservation)
        }
    }
    //Only searchReserve() update observer for now.

    suspend fun populateReserve(){
        viewModelScope.launch {
            val reservation = useCase.populateReserveUseCase()
            reserver.postValue(reservation)
        }
    }

}