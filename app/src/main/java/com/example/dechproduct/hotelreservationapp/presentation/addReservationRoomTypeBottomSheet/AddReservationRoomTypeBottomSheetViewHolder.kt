package com.example.dechproduct.hotelreservationapp.presentation.addReservationRoomTypeBottomSheet

import androidx.recyclerview.widget.RecyclerView
import com.example.dechproduct.hotelreservationapp.databinding.RoomReservationItemBinding
import com.example.dechproduct.hotelreservationapp.databinding.RoomTypeItemBinding



class AddReservationRoomTypeBottomSheetViewHolder (
    val binding: RoomReservationItemBinding

): RecyclerView.ViewHolder(binding.root){

    val rooms = binding.tvRoom
    val selectRoom = binding.btnSelectRoom



}