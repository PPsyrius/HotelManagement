package com.example.dechproduct.hotelreservationapp.presentation.checkinWalk

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dechproduct.hotelreservationapp.data.model.Address
import com.example.dechproduct.hotelreservationapp.data.model.Booking
import com.example.dechproduct.hotelreservationapp.data.model.Room
import com.example.dechproduct.hotelreservationapp.data.model.utility.booking.GuestStatus
import com.example.dechproduct.hotelreservationapp.data.model.utility.booking.PaymentType
import com.example.dechproduct.hotelreservationapp.data.model.utility.booking.VerificationID
import com.example.dechproduct.hotelreservationapp.domain.usecase.UseCase
import com.example.dechproduct.hotelreservationapp.util.Constants
import com.example.dechproduct.hotelreservationapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class CheckinWalkViewModel @Inject constructor(private val useCase: UseCase) : ViewModel() {

    var builtReserve = MutableLiveData<Resource<Booking>>()
    var resolve = MutableLiveData<Resource<Booking>>()

    suspend fun addReserve(
        fname: String, lname: String, phone: String,
        payment: String, verification: String,
        address: String, adult_count: Int, child_count: Int
    ) {
        viewModelScope.launch {
            val reservation =
                useCase.addReserveUseCase(
                    Booking(
                        firstName = fname.capitalize(),
                        lastName = lname.capitalize(),
                        phoneNumber = phone,
                        //TODO: Return and pass PaymentType object, replacing {PaymentType.CASH}
                        paymentType = PaymentType.CASH,
                        verificationID = VerificationID(verification),
                        //TODO: Pass arrival and depart date
                        arrivalDate = Date(),
                        departDate = Date(),
                        adultCount = adult_count,
                        childCount = child_count,
                        address = Address(address),
                        guestPass = null,
                        guestRoom = null,
                        guestStatus = GuestStatus.RESERVED
                    )
                )
            builtReserve.postValue(reservation)
        }
    }

    suspend fun checkInReserve(reservation: Booking) {
        viewModelScope.launch {
            //TODO: Select room from list, then pass it here at 'Room()'
            val response = useCase.checkInGuestUseCase(reservation, Room())
            resolve.postValue(response)
        }
    }
}