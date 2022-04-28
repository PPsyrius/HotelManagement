package com.example.dechproduct.hotelreservationapp.presentation.reservation.add

import android.os.Build
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dechproduct.hotelreservationapp.data.model.Address
import com.example.dechproduct.hotelreservationapp.data.model.Booking
import com.example.dechproduct.hotelreservationapp.data.model.utility.booking.GuestStatus
import com.example.dechproduct.hotelreservationapp.data.model.utility.booking.PaymentType
import com.example.dechproduct.hotelreservationapp.data.model.utility.booking.VerificationID
import com.example.dechproduct.hotelreservationapp.domain.usecase.UseCase
import com.example.dechproduct.hotelreservationapp.util.Constants
import com.example.dechproduct.hotelreservationapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddReservationViewModel @Inject constructor(private val useCase: UseCase) : ViewModel() {

    var reserver = MutableLiveData<Resource<Booking>>()
    var startDateEpoch: Long = 0
    var endDateEpoch: Long = 0

    val amount = MutableLiveData<Int>().apply { value = 0 }
    val amountChild = MutableLiveData<Int>().apply { value = 0 }



    fun increment() {

        //increment amount value by 1 if amount is less than 10
        amount.value?.let { a ->
            amount.value = a + 1

        }
    }

    fun decrement() {

        //decrement amount value by 1 if amount is greater than 0
        amount.value?.let { a ->
            if (a > 0) {
                amount.value = a + -1
            }
        }

    }

    fun incrementChild() {

        //increment amount value by 1 if amount is less than 10
        amountChild.value?.let { a ->
            amountChild.value = a + 1

        }
    }

    fun decrementChild() {

        //decrement amount value by 1 if amount is greater than 0
        amountChild.value?.let { a ->
            if (a > 0) {
                amountChild.value = a + -1
            }
        }
    }

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
                        arrivalDate = Date(startDateEpoch),
                        departDate = Date(endDateEpoch),
                        adultCount = adult_count,
                        childCount = child_count,
                        address = Address(address),
                        guestPass = null,
                        guestRoom = null,
                        guestStatus = GuestStatus.RESERVED
                    )
                )
            reserver.postValue(reservation)
        }
    }
}