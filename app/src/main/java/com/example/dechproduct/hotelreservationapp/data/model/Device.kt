package com.example.dechproduct.hotelreservationapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Device(
    var deviceName: String? = "",
): Parcelable