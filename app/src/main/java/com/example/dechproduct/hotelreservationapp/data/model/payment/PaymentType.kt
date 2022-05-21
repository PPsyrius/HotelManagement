package com.example.dechproduct.hotelreservationapp.data.model.payment

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class PaymentType(
    var displayName: String,

    var internalCode: String,
) : Parcelable {

    CASH("Cash", "CA"),
    CARD_VISA("Visa Card", "VI"),
    CARD_MASTERCARD("Mastercard Card", "MS"),
    EPAY("e-Payment", "EP"),
    None("None","");

    companion object {
        private fun getByInternalCode(key: String) =
            PaymentType.values().find { it.internalCode == key }

        fun pack(paymentType: PaymentType): String = paymentType.internalCode
        fun unpack(paymentType: String): PaymentType? = getByInternalCode(paymentType)
    }
}