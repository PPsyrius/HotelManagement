package com.example.dechproduct.hotelreservationapp.presentation.addReservationRoomTypeBottomSheet

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.example.dechproduct.hotelreservationapp.data.model.Booking
import com.example.dechproduct.hotelreservationapp.databinding.RoomReservationItemBinding
import com.example.dechproduct.hotelreservationapp.databinding.RoomTypeItemBinding
import com.example.dechproduct.hotelreservationapp.domain.usecase.UseCase
import com.example.dechproduct.hotelreservationapp.presentation.roomTypeBottomSheet.RoomTypeViewHolder
import com.example.dechproduct.hotelreservationapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject




class AddReservationRoomTypeBottomSheetAdapter (
    private val bookings: MutableList<Booking>,

    ) : RecyclerView.Adapter <AddReservationRoomTypeBottomSheetViewHolder>(){



    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): AddReservationRoomTypeBottomSheetViewHolder {
        val binding = RoomReservationItemBinding
            .inflate(LayoutInflater.from(viewGroup.context),viewGroup,false)
        return AddReservationRoomTypeBottomSheetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AddReservationRoomTypeBottomSheetViewHolder, index: Int) {


        holder.rooms.text = bookings[index].firstName
//        +"\n"+ bookings[index].lastName

        holder.selectRoom.setOnClickListener { v ->
            Toast.makeText(v.context, "Clicked", Toast.LENGTH_LONG).show()
            // mekh implement here eg: call back FIGHTING!!
        }


    }

    override fun getItemCount() = bookings.size


}