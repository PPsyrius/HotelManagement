package com.example.dechproduct.hotelreservationapp.presentation.checkin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dechproduct.hotelreservationapp.data.model.booking.Booking
import com.example.dechproduct.hotelreservationapp.databinding.CheckInItemBinding
import java.text.SimpleDateFormat

class CheckInAdapter (
    private val bookings: MutableList<Booking>,

    ) : RecyclerView.Adapter <CheckInViewHolder>(){

    var dateFormat = SimpleDateFormat("dd-MM-yyyy")

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
            bookings[index].guest?.firstName +"\n"+ bookings[index].guest?.lastName
        holder.reserveDateIn.text = dateFormat.format(bookings[index].arrivalDate)
        holder.reserveDateOut.text = dateFormat.format(bookings[index].departDate)
        holder.reservePhoneNo.text = bookings[index].guest?.phoneNumber.toString()
        holder.reserveID.text = bookings[index].bookingID.toString()
    }

    override fun getItemCount() = bookings.size

}