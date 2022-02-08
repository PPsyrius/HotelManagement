package com.example.dechproduct.hotelreservationapp.presentation.reservation.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dechproduct.hotelreservationapp.R
import com.example.dechproduct.hotelreservationapp.data.model.Booking

class ReservationsAdapter(private val bookings: MutableList<Booking>)
    :RecyclerView.Adapter<ReservationsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val reserveName: TextView = view.findViewById(R.id.test_title_tv)
        val reserveDateIn: TextView = view.findViewById(R.id.test_desc_tv)
        val reserveDateOut: TextView = view.findViewById(R.id.test_desc_tv2)
        val reservePhoneNo: TextView = view.findViewById(R.id.textView9)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.reservation_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, index: Int) {
        viewHolder.reserveName.text =
            bookings[index].firstName +"\n"+ bookings[index].lastName
        viewHolder.reserveDateIn.text = "From Date: " +
            bookings[index].arrivalDate
        viewHolder.reserveDateOut.text = "To Date: " +
            bookings[index].departDate
        viewHolder.reservePhoneNo.text = "Tel. " +
            bookings[index].phoneNumber
    }

    override fun getItemCount() = bookings.size
}