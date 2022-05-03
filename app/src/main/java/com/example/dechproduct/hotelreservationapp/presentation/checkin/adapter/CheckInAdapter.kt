package com.example.dechproduct.hotelreservationapp.presentation.checkin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dechproduct.hotelreservationapp.data.model.booking.Booking
import com.example.dechproduct.hotelreservationapp.databinding.CheckInItemBinding

class CheckInAdapter (
    private val bookings: MutableList<Booking>,

    ) : RecyclerView.Adapter <CheckInViewHolder>(){

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): CheckInViewHolder {
        val binding = CheckInItemBinding
            .inflate(LayoutInflater.from(viewGroup.context),viewGroup,false)
        return CheckInViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CheckInViewHolder, index: Int) {
        holder.reserveName.text =
            bookings[index].firstName +"\n"+ bookings[index].lastName
        holder.reserveDateIn.text = bookings[index].arrivalDate.toString()
        holder.reserveDateOut.text = bookings[index].departDate.toString()
        holder.reservePhoneNo.text = bookings[index].phoneNumber.toString()
        holder.reserveID.text = bookings[index].bookingID.toString()
    }

    override fun getItemCount() = bookings.size

}