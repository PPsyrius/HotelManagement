package com.example.dechproduct.hotelreservationapp.presentation.roomTypeBottomSheet


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.dechproduct.hotelreservationapp.data.model.booking.Booking
import com.example.dechproduct.hotelreservationapp.data.model.room.RoomType
import com.example.dechproduct.hotelreservationapp.databinding.RoomTypeItemBinding


class RoomTypeAdapter (
    private val roomTypes: MutableList<RoomType>,
    private val onRecyclerItemClicked: (RoomType) -> Unit


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


//        holder.rooms.text = bookings[index].guest?.firstName

        holder.rooms.text = roomTypes[index].toString()
//        +"\n"+ bookings[index].lastName

        holder.selectRoom.setOnClickListener { v ->
            onRecyclerItemClicked.invoke(roomTypes[index])

        }


    }

    override fun getItemCount() = roomTypes.size


}