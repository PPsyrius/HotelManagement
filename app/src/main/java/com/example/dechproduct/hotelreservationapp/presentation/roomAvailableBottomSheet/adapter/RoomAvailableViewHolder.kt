package com.example.dechproduct.hotelreservationapp.presentation.roomAvailableBottomSheet.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.dechproduct.hotelreservationapp.databinding.RoomAvailableItemBinding

class RoomAvailableViewHolder (
    val binding: RoomAvailableItemBinding

    //TODO: Handle Layout To responsive (GUIDE: Add Reservation Activity.xml) --TUNG

): RecyclerView.ViewHolder(binding.root){

    val rooms = binding.tvRoom
    val roomStatus = binding.tvRoomStatusDisplay
    val roomPrice= binding.tvRoomPriceDisplay
    val selectRoom = binding.btnSelectRoom



}


