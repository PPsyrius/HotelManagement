package com.example.dechproduct.hotelreservationapp.presentation.addReservationRoomBedBottomSheet

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.dechproduct.hotelreservationapp.data.model.booking.Booking
import com.example.dechproduct.hotelreservationapp.databinding.RoomBedReservationItemBinding





class AddReservationRoomBedBottomSheetAdapter (
    private val bookings: MutableList<Booking>,

    ) : RecyclerView.Adapter <AddReservationRoomBedBottomSheetViewHolder>(){



    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): AddReservationRoomBedBottomSheetViewHolder {
        val binding = RoomBedReservationItemBinding
            .inflate(LayoutInflater.from(viewGroup.context),viewGroup,false)
        return AddReservationRoomBedBottomSheetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AddReservationRoomBedBottomSheetViewHolder, index: Int) {

        holder.rooms.text = "Test"
//        holder.rooms.text = bookings[index].firstName
//        +"\n"+ bookings[index].lastName

        holder.selectRoom.setOnClickListener { v ->
            Toast.makeText(v.context, "Clicked", Toast.LENGTH_LONG).show()
            // mekh implement here eg: call back FIGHTING!!
        }


    }

    override fun getItemCount() = bookings.size


}