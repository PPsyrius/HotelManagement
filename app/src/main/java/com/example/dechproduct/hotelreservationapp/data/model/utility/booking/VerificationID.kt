package com.example.dechproduct.hotelreservationapp.data.model.utility.booking

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class VerificationID(
    var id: String,
    var url: String = ""
    //TODO: Implement photo path here
) : Parcelable {
    lateinit var type: Type

    enum class Type {
        THAI, FOREIGNER
    }

    init {
        identifyID()
    }

    private fun identifyID() {
        type = if (id.matches("\\d{13}".toRegex())) {
            Type.THAI
        } else {
            Type.FOREIGNER
        }
    }
}