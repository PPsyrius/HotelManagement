package com.example.dechproduct.hotelreservationapp.presentation.reservation.add

import android.app.Application
import android.graphics.Bitmap
import android.util.Base64
import android.util.Log
import android.widget.CheckBox
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dechproduct.hotelreservationapp.data.model.unused.Address
import com.example.dechproduct.hotelreservationapp.data.model.booking.Booking
import com.example.dechproduct.hotelreservationapp.data.model.booking.BookingStatus
import com.example.dechproduct.hotelreservationapp.data.model.guest.Guest
import com.example.dechproduct.hotelreservationapp.data.model.guest.Ticket
import com.example.dechproduct.hotelreservationapp.data.model.payment.PaymentType
import com.example.dechproduct.hotelreservationapp.data.model.payment.Payment
import com.example.dechproduct.hotelreservationapp.data.model.room.*
import com.example.dechproduct.hotelreservationapp.domain.usecase.UseCase
import com.example.dechproduct.hotelreservationapp.util.Constants
import com.example.dechproduct.hotelreservationapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddReservationViewModel @Inject constructor(var useCase: UseCase) : ViewModel() {

    var reserver = MutableLiveData<Resource<Booking>>()
    var roomer = MutableLiveData<Resource<List<Room>>>()
    var loadedReservation = MutableLiveData<Resource<Booking>>()

    var startDateEpoch: Long = 0
    var endDateEpoch: Long = 0

    val amount = MutableLiveData<Int>().apply { value = 0 }
    val amountChild = MutableLiveData<Int>().apply { value = 0 }
    val checkedBreakfast = MutableLiveData<Boolean>().apply { value = false }
    val checkedSmoking = MutableLiveData<Boolean>().apply { value = false }
    var roomType : MutableLiveData<RoomType> = MutableLiveData()
    var bedType : MutableLiveData<BedType> = MutableLiveData()
    var paymentPhoto: Bitmap? = null
    var idPhoto: Bitmap? = null
    var toOccupy: Occupancy = Occupancy(null, null, OccupancyStatus.NONE)
    var reservation: Booking = Booking(
        guest = Guest(),
        ticket = Ticket(),
        room = Room(),
        payment = Payment(),
        status = BookingStatus.CREATED,
    )

    var cameraState: Int = 0

    fun breakfastChecked() {
        checkedBreakfast.value?.let { a ->
            checkedBreakfast.value = true
        }

    }

    fun breakfastNotChecked() {
        checkedBreakfast.value?.let { a ->
            checkedBreakfast.value = false
        }

    }

    fun smokingChecked() {
        checkedSmoking.value?.let { a ->
            checkedSmoking.value = true
        }

    }

    fun smokingNotChecked() {
        checkedSmoking.value?.let { a ->
            checkedSmoking.value = false
        }

    }


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
            if (a > Constants.CAPACITY_MAX_CHILD) {
                amountChild.value = a - 1
            }
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

    fun checkRoomAvailable() {
        viewModelScope.launch {
            try {

                val room = useCase.searchRoomUseCase(
                    roomType = reservation.room!!.type!!,
                    bedType = reservation.room!!.beds!!,
                    smoking = reservation.room!!.smoking!!,
                    //Can add RoomStatus.LEAVING for rooms expected to be vacant.
                    status = listOf<RoomStatus>(RoomStatus.READY),
                    occupancy = Occupancy(
                        toOccupy.arrivalDate,
                        toOccupy.departDate,
                        OccupancyStatus.NONE
                    ),
                    adult_count = reservation.adultCount!!, child_count = reservation.childCount!!
                )
                roomer.postValue(room)
            } catch (e: Exception) {
                Log.d("ERR:", e.toString())
            }

        }
    }

    fun updateInfo(reserved: String) {
        viewModelScope.launch {
            val reserving = useCase.getReserveByIDUseCase(reserved)
            loadedReservation.postValue(reserving)
        }
    }

    fun addReservation() {
        viewModelScope.launch {
            try {
                reservation.status = BookingStatus.RESERVED
                reservation.arrivalDate = toOccupy.arrivalDate
                reservation.departDate = toOccupy.departDate
                reservation.room!!.occupancy!!.add(
                    Occupancy(
                        arrivalDate = toOccupy.arrivalDate,
                        departDate = toOccupy.departDate,
                        status = OccupancyStatus.TALLY
                    )
                )

                val reserving = useCase.addReserveUseCase(reservation)
                reservation.room?.let { useCase.editRoomUseCase(it) }

                reserver.postValue(reserving)
            } catch (e: Exception) {
                Log.d("ERR:", e.toString())
            }


        }

    }
}