package com.example.dechproduct.hotelreservationapp.presentation.reservation.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dechproduct.hotelreservationapp.data.model.Booking
import com.example.dechproduct.hotelreservationapp.databinding.ReservationItemBinding

class SearchAdapter(
    private val bookings: MutableList<Booking>,
)
    :RecyclerView.Adapter<SearchViewHolder>() {

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): SearchViewHolder {
        val binding = ReservationItemBinding
            .inflate(LayoutInflater.from(viewGroup.context),viewGroup,false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, index: Int) {
        holder.reserveName.text =
            bookings[index].firstName +"\n"+ bookings[index].lastName
        holder.reserveDateIn.text = bookings[index].arrivalDate.toString()
        holder.reserveDateOut.text = bookings[index].departDate.toString()
        holder.reservePhoneNo.text = bookings[index].phoneNumber.toString()
    }

    override fun getItemCount() = bookings.size
}