package com.example.dechproduct.hotelreservationapp.presentation.roomBedBottomSheet.adapter.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.dechproduct.hotelreservationapp.data.model.Booking
import com.example.dechproduct.hotelreservationapp.databinding.RoomBedItemBinding


class RoomBedAdapter (
    private val bookings: MutableList<Booking>,

    ) : RecyclerView.Adapter <RoomBedViewHolder>(){



    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): RoomBedViewHolder{
        val binding = RoomBedItemBinding
            .inflate(LayoutInflater.from(viewGroup.context),viewGroup,false)
        return RoomBedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RoomBedViewHolder, index: Int) {


        holder.rooms.text = bookings[index].firstName
//        +"\n"+ bookings[index].lastName

        holder.selectRoom.setOnClickListener { v ->
            Toast.makeText(v.context, "Clicked", Toast.LENGTH_LONG).show()
            // mekh implement here eg: call back FIGHTING!!
        }


    }

    override fun getItemCount() = bookings.size


}