package com.example.dechproduct.hotelreservationapp.presentation.checkin.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.dechproduct.hotelreservationapp.databinding.CheckInItemBinding

class CheckInViewHolder (
    val binding: CheckInItemBinding

    ): RecyclerView.ViewHolder(binding.root){

    val reserveName = binding.testTitleTv
    val reserveDateIn = binding.testDescTv
    val reserveDateOut= binding.testDescTv2
    val reservePhoneNo = binding.textView9
    val reserveID = binding.textView6

}