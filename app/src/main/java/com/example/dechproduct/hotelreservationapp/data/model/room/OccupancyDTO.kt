package com.example.dechproduct.hotelreservationapp.data.model.room

import android.os.Parcelable
import com.example.dechproduct.hotelreservationapp.util.Constants
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat

@Parcelize
data class OccupancyDTO(
    @SerializedName(Constants.API_ROOM_KEY_DATE_IN)
    var arrivalDate: String?,
    @SerializedName(Constants.API_ROOM_KEY_DATE_OUT)
    var departDate: String?,
    @SerializedName(Constants.API_ROOM_KEY_STATUS)
    var status: String?

): Parcelable {
    fun toOccupancy(): Occupancy {
        var dateFormat = SimpleDateFormat("dd-MM-yyyy")
        return Occupancy(
            arrivalDate = dateFormat.parse(arrivalDate),
            departDate = dateFormat.parse(departDate),
            status = status?.let { OccupancyStatus.unpack(it) }
        )
    }
    companion object{
        fun toListOfOccupancy(occupancies: List<OccupancyDTO>):List<Occupancy>{
            var parcel: MutableList<Occupancy> = mutableListOf<Occupancy>()
            for (occupancy in occupancies) {
                parcel.add(occupancy.toOccupancy())
            }
            return parcel
        }
    }
}