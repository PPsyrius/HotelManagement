package com.example.dechproduct.hotelreservationapp.presentation.reservation.search.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dechproduct.hotelreservationapp.R
import com.example.dechproduct.hotelreservationapp.databinding.ReservationItemBinding


class SearchViewHolder(
       val binding: ReservationItemBinding

   ) : RecyclerView.ViewHolder(binding.root) {
       val reserveName = binding.testTitleTv
       val reserveDateIn = binding.testDescTv
       val reserveDateOut= binding.testDescTv2
       val reservePhoneNo = binding.textView9
   }
