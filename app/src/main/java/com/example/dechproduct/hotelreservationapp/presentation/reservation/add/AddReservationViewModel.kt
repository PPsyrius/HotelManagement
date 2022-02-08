package com.example.dechproduct.hotelreservationapp.presentation.reservation.add

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dechproduct.hotelreservationapp.data.model.Address
import com.example.dechproduct.hotelreservationapp.data.model.Booking
import com.example.dechproduct.hotelreservationapp.domain.usecase.UseCase
import com.example.dechproduct.hotelreservationapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddReservationViewModel @Inject constructor(private val useCase: UseCase): ViewModel(){

    var reserver = MutableLiveData<Resource<Booking>>()

    suspend fun addReserve(fname : String,lname  : String,phone : String,
                           payment : String, unidentified_id : String,
                           sta_date : String,end_date : String,address : String){

        //check TH-ssn
        if (unidentified_id.matches("\\d{13}".toRegex())){
            viewModelScope.launch {
                val reservation = useCase.addReserveUseCase(
                    Booking(
                        firstName = fname, lastName = lname, phoneNumber = phone,
                        paymentType = payment, verificationID = unidentified_id,
                        arrivalDate = sta_date, departDate = end_date, address = Address(address),
                        guestPass = null, guestRoom = null,
                    ))
                reserver.postValue(reservation)
            }
        }

        //else not TH-ssn, no usage as of now.
        else{
            viewModelScope.launch {
                val reservation = useCase.addReserveUseCase(
                    //do same thing as TH-ssn
                    Booking(
                        firstName = fname, lastName = lname, phoneNumber = phone,
                        paymentType = payment, verificationID = unidentified_id,
                        arrivalDate = sta_date, departDate = end_date, address = Address(address),
                        guestPass = null, guestRoom = null,
                    ))
                reserver.postValue(reservation)
            }
        }
    }
}