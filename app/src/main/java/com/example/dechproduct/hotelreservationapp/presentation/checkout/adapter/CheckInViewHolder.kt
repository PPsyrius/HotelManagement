package com.example.dechproduct.hotelreservationapp.presentation.checkout.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.dechproduct.hotelreservationapp.databinding.CheckInItemBinding
import com.example.dechproduct.hotelreservationapp.databinding.CheckOutItemBinding

class CheckOutViewHolder (
    val binding: CheckOutItemBinding

    ): RecyclerView.ViewHolder(binding.root){

    val reserveName = binding.testTitleTv
    val reserveDateIn = binding.testDescTv
    val reserveDateOut= binding.testDescTv2
    val reservePhoneNo = binding.textView9
    val reserveID = binding.textView6
}