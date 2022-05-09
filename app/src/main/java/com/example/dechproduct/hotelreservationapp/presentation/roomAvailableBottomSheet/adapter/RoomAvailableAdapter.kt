package com.example.dechproduct.hotelreservationapp.presentation.roomAvailableBottomSheet.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.dechproduct.hotelreservationapp.data.model.booking.Booking
import com.example.dechproduct.hotelreservationapp.data.model.room.Room
import com.example.dechproduct.hotelreservationapp.databinding.RoomAvailableItemBinding


class RoomAvailableAdapter(
    private val rooms: MutableList<Room>,
    private val onRecyclerItemClicked: (Room) -> Unit

) : RecyclerView.Adapter<RoomAvailableViewHolder>() {


    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): RoomAvailableViewHolder {
        val binding = RoomAvailableItemBinding
            .inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return RoomAvailableViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RoomAvailableViewHolder, index: Int) {

        holder.rooms.text = rooms[index].roomID
        holder.roomStatus.text = rooms[index].status?.displayName
        holder.roomPrice.text = rooms[index].price.toString()

        holder.selectRoom.setOnClickListener { v ->
            onRecyclerItemClicked.invoke(rooms[index])
        }

    }

    override fun getItemCount() = rooms.size


}