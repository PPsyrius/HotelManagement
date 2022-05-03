package com.example.dechproduct.hotelreservationapp.presentation.checkinDetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dechproduct.hotelreservationapp.data.model.booking.Booking
import com.example.dechproduct.hotelreservationapp.data.model.room.Room
import com.example.dechproduct.hotelreservationapp.domain.usecase.UseCase
import com.example.dechproduct.hotelreservationapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CheckinDetailViewModel @Inject constructor(private val useCase: UseCase) : ViewModel() {

    val amount = MutableLiveData<Int>().apply { value = 0 }
    val amountChild = MutableLiveData<Int>().apply { value = 0 }

    var selected = MutableLiveData<Resource<Booking>>()
    var resolve = MutableLiveData<Resource<Booking>>()
    lateinit var reservation: Booking
//    @SuppressLint("StaticFieldLeak")
//    private val context: Context = getApplication<Application>().applicationContext

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

    suspend fun updateInfo(reserved: String) {
        viewModelScope.launch {
            val reservation = useCase.getReserveByIDUseCase(reserved)
            selected.postValue(reservation)
        }
    }

    suspend fun checkInReserved() {
        viewModelScope.launch {
            //TODO: Select room from list, then pass it here at 'Room()' --Mekh
            val response = useCase.checkInGuestUseCase(reservation, Room())
            resolve.postValue(response)
        }
    }
}