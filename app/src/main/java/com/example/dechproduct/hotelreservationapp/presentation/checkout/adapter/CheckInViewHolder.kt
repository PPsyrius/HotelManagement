package com.example.dechproduct.hotelreservationapp.presentation.checkout.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.dechproduct.hotelreservationapp.databinding.CheckInItemBinding
import com.example.dechproduct.hotelreservationapp.databinding.CheckOutItemBinding

class CheckOutViewHolder (
    val binding: CheckOutItemBinding

    ): RecyclerView.ViewHolder(binding.root){

    val checkOutName = binding.testTitleTv
    val checkOutDateIn = binding.testDescTv
    val checkOutDateOut= binding.testDescTv2
    val checkOutPhoneNumber = binding.textView9
    val checkOutID = binding.textView6
}