package com.example.dechproduct.hotelreservationapp.presentation.addReservationRoomTypeBottomSheet

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.dechproduct.hotelreservationapp.data.model.booking.Booking
import com.example.dechproduct.hotelreservationapp.databinding.RoomReservationItemBinding


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