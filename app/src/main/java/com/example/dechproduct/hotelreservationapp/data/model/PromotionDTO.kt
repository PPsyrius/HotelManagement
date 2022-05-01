package com.example.dechproduct.hotelreservationapp.data.model

import android.os.Parcelable
import com.example.dechproduct.hotelreservationapp.data.model.utility.room.Feature
import com.example.dechproduct.hotelreservationapp.data.model.utility.room.RoomStatus
import com.example.dechproduct.hotelreservationapp.util.Constants
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class PromotionDTO(
    @SerializedName(Constants.API_INFO_KEY_TITLE)
    var title: String?,
    @SerializedName(Constants.API_INFO_KEY_DESC)
    var description: String?,
    @SerializedName(Constants.API_INFO_KEY_PARTNER)
    var partners: List<String>?,
    @SerializedName(Constants.API_INFO_KEY_DISCOUNT)
    var discount: Double?
) {
    override fun toString(): String {
        return title.toString() + "\n" + description.toString()
    }

    fun toPromotion(): Promotion {
        return Promotion(
            title = title,
            description = description,
            partners = partners,
            discount = discount
        )
    }
}