package com.example.dechproduct.hotelreservationapp.data.model.utility.booking

import android.os.Parcelable
import com.example.dechproduct.hotelreservationapp.data.model.utility.room.RoomStatus
import kotlinx.parcelize.Parcelize

@Parcelize
enum class PaymentType(
    var displayName: String,

    var internalCode: String,
) : Parcelable {
    CASH("Cash", "CA");

    companion object {
        private fun getByInternalCode(key: String) = PaymentType.values().find { it.internalCode == key }
        fun pack(paymentType: PaymentType): String = paymentType.internalCode
        fun unpack(paymentType: String): PaymentType? = PaymentType.getByInternalCode(paymentType)
    }
}