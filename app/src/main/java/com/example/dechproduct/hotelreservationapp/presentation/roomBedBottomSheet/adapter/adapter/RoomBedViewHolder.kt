package com.example.dechproduct.hotelreservationapp.presentation.roomBedBottomSheet.adapter.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.dechproduct.hotelreservationapp.databinding.RoomAvailableItemBinding
import com.example.dechproduct.hotelreservationapp.databinding.RoomBedItemBinding

class RoomBedViewHolder (
    val binding: RoomBedItemBinding

): RecyclerView.ViewHolder(binding.root){

    val rooms = binding.tvRoom
    val selectRoom = binding.btnSelectRoom



}
