package com.example.dechproduct.hotelreservationapp.presentation.checkout.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dechproduct.hotelreservationapp.data.model.booking.Booking
import com.example.dechproduct.hotelreservationapp.databinding.CheckOutItemBinding

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
        holder.checkOutName.text =
            bookings[index].firstName +"\n"+ bookings[index].lastName
        holder.checkOutDateIn.text = bookings[index].arrivalDate.toString()
        holder.checkOutDateOut.text = bookings[index].departDate.toString()
        holder.checkOutPhoneNumber.text = bookings[index].phoneNumber.toString()
        holder.checkOutID.text = bookings[index].bookingID.toString()
    }
    
    override fun getItemCount() = bookings.size

}