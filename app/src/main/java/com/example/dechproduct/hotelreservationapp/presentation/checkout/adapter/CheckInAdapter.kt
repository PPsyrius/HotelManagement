package com.example.dechproduct.hotelreservationapp.presentation.checkout.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dechproduct.hotelreservationapp.data.model.Booking
import com.example.dechproduct.hotelreservationapp.databinding.CheckInItemBinding
import com.example.dechproduct.hotelreservationapp.databinding.CheckOutItemBinding
import com.example.dechproduct.hotelreservationapp.databinding.ReservationItemBinding
import com.example.dechproduct.hotelreservationapp.presentation.reservation.search.adapter.SearchViewHolder

class CheckOutAdapter (
    private val bookings: MutableList<Booking>,

    ) : RecyclerView.Adapter <CheckOutViewHolder>(){
    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): CheckOutViewHolder {
        val binding = CheckOutItemBinding
            .inflate(LayoutInflater.from(viewGroup.context),viewGroup,false)
        return CheckOutViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CheckOutViewHolder, index: Int) {
        holder.reserveName.text =
            bookings[index].firstName +"\n"+ bookings[index].lastName
        holder.reserveDateIn.text = bookings[index].arrivalDate.toString()
        holder.reserveDateOut.text = bookings[index].departDate.toString()
        holder.reservePhoneNo.text = bookings[index].phoneNumber.toString()
    }

    override fun getItemCount() = bookings.size

}