package com.example.dechproduct.hotelreservationapp.presentation.checkout.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dechproduct.hotelreservationapp.data.model.booking.Booking
import com.example.dechproduct.hotelreservationapp.databinding.CheckOutItemBinding
import java.text.SimpleDateFormat

class CheckOutAdapter (
    private val bookings: MutableList<Booking>,

    ) : RecyclerView.Adapter <CheckOutViewHolder>(){

    var dateFormat = SimpleDateFormat("dd-MM-yyyy")

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
            bookings[index].guest?.firstName +"\n"+ bookings[index].guest?.lastName
        holder.checkOutDateIn.text = dateFormat.format(bookings[index].arrivalDate)
        holder.checkOutDateOut.text = dateFormat.format(bookings[index].departDate)
        holder.checkOutPhoneNumber.text = bookings[index].guest?.phoneNumber.toString()
        holder.checkOutID.text = bookings[index].bookingID.toString()
    }
    
    override fun getItemCount() = bookings.size

}