package com.example.dechproduct.hotelreservationapp.presentation.roomAvailableBottomSheet.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.dechproduct.hotelreservationapp.data.model.Booking
import com.example.dechproduct.hotelreservationapp.databinding.RoomAvailableItemBinding


class RoomAvailableAdapter (
    private val bookings: MutableList<Booking>,

    ) : RecyclerView.Adapter <RoomAvailableViewHolder>(){



    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): RoomAvailableViewHolder {
        val binding = RoomAvailableItemBinding
            .inflate(LayoutInflater.from(viewGroup.context),viewGroup,false)
        return RoomAvailableViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RoomAvailableViewHolder, index: Int) {


        holder.rooms.text = bookings[index].firstName
//        +"\n"+ bookings[index].lastName
        holder.roomStatus.text = bookings[index].guestStatus

        holder.roomPrice.text = "10,000"+ " ฿"

        holder.selectRoom.setOnClickListener { v ->
            Toast.makeText(v.context, "Clicked", Toast.LENGTH_LONG).show()
            // mekh implement here eg: call back FIGHTING!!
        }


    }

    override fun getItemCount() = bookings.size


}