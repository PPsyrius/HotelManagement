package com.example.dechproduct.hotelreservationapp.data.model

import android.os.Parcelable
import com.example.dechproduct.hotelreservationapp.data.model.utility.room.Feature
import com.example.dechproduct.hotelreservationapp.data.model.utility.room.RoomStatus
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Promotion(
    var title: String?,
    var description: String?,
    var partners: List<String>?,
    var discount: Double?
): Parcelable{
    override fun toString(): String {
        return title.toString() + "\n" + description.toString()
    }

    fun toPromotionDTO(): PromotionDTO{
        return PromotionDTO(
            title = title,
            description = description,
            partners = partners,
            discount = discount
        )
    }
}