package com.example.dechproduct.hotelreservationapp.presentation.roomBedBottomSheet.adapter.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.dechproduct.hotelreservationapp.data.model.booking.Booking
import com.example.dechproduct.hotelreservationapp.data.model.room.BedType
import com.example.dechproduct.hotelreservationapp.databinding.RoomBedItemBinding


class RoomBedAdapter (
    private val bedTypes: MutableList<BedType>,
    private val onRecyclerItemClicked: (BedType) -> Unit


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


        holder.rooms.text = bedTypes[index].toString()
//        +"\n"+ bookings[index].lastName

        holder.selectRoom.setOnClickListener { v ->
            onRecyclerItemClicked.invoke(bedTypes[index])
            // mekh implement here eg: call back FIGHTING!!
        }


    }

    override fun getItemCount() = bedTypes.size


}