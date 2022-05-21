package com.example.dechproduct.hotelreservationapp.presentation.reservation.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dechproduct.hotelreservationapp.data.model.booking.Booking
import com.example.dechproduct.hotelreservationapp.databinding.ReservationItemBinding
import java.text.SimpleDateFormat

class SearchAdapter(
    private val bookings: MutableList<Booking>,
)
    :RecyclerView.Adapter<SearchViewHolder>() {

    var dateFormat = SimpleDateFormat("dd-MM-yyyy")

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
            bookings[index].guest?.firstName +"\n"+ bookings[index].guest?.lastName
        holder.reserveDateIn.text = dateFormat.format(bookings[index].arrivalDate)
        holder.reserveDateOut.text = dateFormat.format(bookings[index].departDate)
        holder.reservePhoneNo.text = bookings[index].guest?.phoneNumber.toString()
        holder.reserveRoomType.text = bookings[index].bookingID.toString()

    }

    override fun getItemCount() = bookings.size
}