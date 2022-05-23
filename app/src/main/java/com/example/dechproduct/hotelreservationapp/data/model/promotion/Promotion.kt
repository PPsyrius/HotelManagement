package com.example.dechproduct.hotelreservationapp.data.model.promotion

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Promotion(
    var promotionID: String,
    var title: String?,
    var description: String?,
    var partners: List<Partner>?,
    var discount: Double?
): Parcelable{
    override fun toString(): String {
        return title.toString() + "\n" + description.toString()
    }

    fun toPromotionDTO(): PromotionDTO {
        return PromotionDTO(
            promotionID = promotionID,
            title = title,
            description = description,
            partners = partners?.let { Partner.toListOfStrings(it) },
            discount = discount
        )
    }
}