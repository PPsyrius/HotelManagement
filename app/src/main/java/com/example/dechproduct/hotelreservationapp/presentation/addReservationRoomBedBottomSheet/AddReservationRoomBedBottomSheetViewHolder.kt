package com.example.dechproduct.hotelreservationapp.presentation.addReservationRoomBedBottomSheet

import androidx.recyclerview.widget.RecyclerView
import com.example.dechproduct.hotelreservationapp.databinding.RoomBedReservationItemBinding
import com.example.dechproduct.hotelreservationapp.databinding.RoomReservationItemBinding
import com.example.dechproduct.hotelreservationapp.databinding.RoomTypeItemBinding



class AddReservationRoomBedBottomSheetViewHolder (
    val binding: RoomBedReservationItemBinding

): RecyclerView.ViewHolder(binding.root){

    val rooms = binding.tvRoom
    val selectRoom = binding.btnSelectRoom



}