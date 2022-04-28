package com.example.dechproduct.hotelreservationapp.presentation.roomTypeBottomSheet

import androidx.recyclerview.widget.RecyclerView
import com.example.dechproduct.hotelreservationapp.databinding.RoomAvailableItemBinding
import com.example.dechproduct.hotelreservationapp.databinding.RoomBedItemBinding
import com.example.dechproduct.hotelreservationapp.databinding.RoomTypeItemBinding

class RoomTypeViewHolder (
    val binding: RoomTypeItemBinding

): RecyclerView.ViewHolder(binding.root){

    val rooms = binding.tvRoom
    val selectRoom = binding.btnSelectRoom



}
