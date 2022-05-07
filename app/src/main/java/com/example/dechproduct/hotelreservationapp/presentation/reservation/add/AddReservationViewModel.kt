package com.example.dechproduct.hotelreservationapp.presentation.reservation.add

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dechproduct.hotelreservationapp.data.model.unused.Address
import com.example.dechproduct.hotelreservationapp.data.model.booking.Booking
import com.example.dechproduct.hotelreservationapp.data.model.booking.BookingStatus
import com.example.dechproduct.hotelreservationapp.data.model.guest.Guest
import com.example.dechproduct.hotelreservationapp.data.model.payment.PaymentType
import com.example.dechproduct.hotelreservationapp.data.model.guest.VerificationID
import com.example.dechproduct.hotelreservationapp.data.model.payment.Payment
import com.example.dechproduct.hotelreservationapp.domain.usecase.UseCase
import com.example.dechproduct.hotelreservationapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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
        address: List<String>, adult_count: Int, child_count: Int,
        breakfast: Boolean, isAddonBed: Boolean
    ) {
        viewModelScope.launch {
            val reservation =
                useCase.addReserveUseCase(
                    Booking(
                        bookingID = null,
                        guest = Guest(
                            guestID = null,
                            firstName = fname.capitalize(),
                            lastName = lname.capitalize(),
                            phoneNumber = phone,
                            address = address,
                            region = null,
                            postalCode = null,
                            country = null,
                            verificationID = VerificationID(verification),
                            verificationPhoto = null
                        ),
                        ticket = null,
                        room = null,
                        payment = Payment(
                            paymentID = null,
                            type = PaymentType.unpack(payment),
                            //TODO: Return and pass PaymentType object, replacing {PaymentType.CASH} -- TUNG
                            photo = null
                        ),
                        arrivalDate = Date(startDateEpoch),
                        departDate = Date(endDateEpoch),
                        adultCount = adult_count,
                        childCount = child_count,
                        status = BookingStatus.CREATED,
                        breakfast = breakfast,
                        isAddonBed = isAddonBed
                    )
                )
            reserver.postValue(reservation)
        }
    }
}