package com.example.dechproduct.hotelreservationapp.data.model.unused

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Address(
    var firstRow: String? = "",
    var secondRow: String? = "",
    var region: String? = "",
    var country: String? = "",
): Parcelable