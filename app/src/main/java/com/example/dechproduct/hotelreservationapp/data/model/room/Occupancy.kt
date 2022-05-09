package com.example.dechproduct.hotelreservationapp.data.model.room

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
data class Occupancy(

    var arrivalDate: Date?,

    var departDate: Date?,

    var status: OccupancyStatus?

) : Parcelable {
    fun toOccupancyDTO(): OccupancyDTO {
        var dateFormat = SimpleDateFormat("dd-MM-yyyy")
        return OccupancyDTO(
            arrivalDate = dateFormat.format(arrivalDate),
            departDate = dateFormat.format(departDate),
            status = status?.let { OccupancyStatus.pack(it) }
        )
    }
    companion object{
        fun toListOfOccupancyDTO(occupancies: List<Occupancy>):List<OccupancyDTO>{
            var parcel: MutableList<OccupancyDTO> = mutableListOf<OccupancyDTO>()
            for (occupancy in occupancies) {
                parcel.add(occupancy.toOccupancyDTO())
            }
            return parcel
        }
    }
}