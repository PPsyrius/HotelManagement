package com.example.dechproduct.hotelreservationapp.data.model.promotion

import com.example.dechproduct.hotelreservationapp.util.Constants
import com.google.gson.annotations.SerializedName

data class PromotionDTO(
    @SerializedName(Constants.API_PROMOTION_KEY_ID)
    var promotionID: String,
    @SerializedName(Constants.API_PROMOTION_KEY_TITLE)
    var title: String?,
    @SerializedName(Constants.API_PROMOTION_KEY_DESCRIPTION)
    var description: String?,
    @SerializedName(Constants.API_PROMOTION_KEY_PARTNER)
    var partners: List<String>?,
    @SerializedName(Constants.API_PROMOTION_KEY_DISCOUNT)
    var discount: Double?
) {
    override fun toString(): String {
        return title.toString() + "\n" + description.toString()
    }

    fun toPromotion(): Promotion {
        return Promotion(
            promotionID = promotionID,
            title = title,
            description = description,
            partners = partners,
            discount = discount
        )
    }
}