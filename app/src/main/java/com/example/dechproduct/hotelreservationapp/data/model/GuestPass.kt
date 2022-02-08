package com.example.dechproduct.hotelreservationapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GuestPass(
    var userName: String,
    var password: String,
): Parcelable