package com.example.dechproduct.hotelreservationapp.data.model.guest

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class VerificationID(
    var id: String = "",

) : Parcelable {
    lateinit var type: Type
    enum class Type {
        THAI, FOREIGNER
    }

    init {
        identifyID()
    }

    fun identifyID() {
        type = if (id.matches("\\d{13}".toRegex())) {
            Type.THAI
        } else {
            Type.FOREIGNER
        }
    }

    override fun toString(): String {
        return id.toString()
    }
}