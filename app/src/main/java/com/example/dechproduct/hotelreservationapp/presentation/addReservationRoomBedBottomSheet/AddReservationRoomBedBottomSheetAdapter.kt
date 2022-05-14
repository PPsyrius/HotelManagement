package com.example.dechproduct.hotelreservationapp.presentation.addReservationRoomBedBottomSheet

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.dechproduct.hotelreservationapp.data.model.booking.Booking
import com.example.dechproduct.hotelreservationapp.data.model.room.BedType
import com.example.dechproduct.hotelreservationapp.data.model.room.RoomType
import com.example.dechproduct.hotelreservationapp.databinding.RoomBedReservationItemBinding


class AddReservationRoomBedBottomSheetAdapter (
    private val bedTypes: MutableList<BedType>,
    private val onRecyclerItemClicked: (BedType) -> Unit

    ) : RecyclerView.Adapter <AddReservationRoomBedBottomSheetViewHolder>(){

//TODO: Arrange file into correct package (e.g. adapter folder)

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): AddReservationRoomBedBottomSheetViewHolder {
        val binding = RoomBedReservationItemBinding
            .inflate(LayoutInflater.from(viewGroup.context),viewGroup,false)
        return AddReservationRoomBedBottomSheetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AddReservationRoomBedBottomSheetViewHolder, index: Int) {

        holder.rooms.text = bedTypes[index].toString()
//        holder.rooms.text = bedTypes[index].firstName
//        +"\n"+ bedTypes[index].lastName

        holder.selectRoom.setOnClickListener { v ->
            onRecyclerItemClicked.invoke(bedTypes[index])
            // mekh implement here eg: call back FIGHTING!!
        }


    }

    override fun getItemCount() = bedTypes.size


}