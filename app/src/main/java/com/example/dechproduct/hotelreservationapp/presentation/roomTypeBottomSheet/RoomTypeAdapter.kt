package com.example.dechproduct.hotelreservationapp.presentation.roomTypeBottomSheet


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.dechproduct.hotelreservationapp.data.model.Booking
import com.example.dechproduct.hotelreservationapp.databinding.RoomTypeItemBinding


class RoomTypeAdapter (
    private val bookings: MutableList<Booking>,

    ) : RecyclerView.Adapter <RoomTypeViewHolder>(){



    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): RoomTypeViewHolder{
        val binding = RoomTypeItemBinding
            .inflate(LayoutInflater.from(viewGroup.context),viewGroup,false)
        return RoomTypeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RoomTypeViewHolder, index: Int) {


        holder.rooms.text = bookings[index].firstName
//        +"\n"+ bookings[index].lastName

        holder.selectRoom.setOnClickListener { v ->
            Toast.makeText(v.context, "Clicked", Toast.LENGTH_LONG).show()
            // mekh implement here eg: call back FIGHTING!!
        }


    }

    override fun getItemCount() = bookings.size


}