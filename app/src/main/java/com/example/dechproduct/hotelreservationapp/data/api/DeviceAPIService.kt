package com.example.dechproduct.hotelreservationapp.data.api

import com.example.dechproduct.hotelreservationapp.data.model.*
import com.example.dechproduct.hotelreservationapp.data.model.device.Device
import com.example.dechproduct.hotelreservationapp.data.model.device.DeviceDTO
import com.example.dechproduct.hotelreservationapp.util.Constants
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface DeviceAPIService {
    //Unused for the scope

    @GET(Constants.API_DEVICE_INDEX_URL)
    suspend fun getAllDevice(): List<DeviceDTO>

    @GET(Constants.API_DEVICE_INDEX_URL)
    suspend fun getBySerial(
        @Query(Constants.API_DEVICE_KEY_SERIAL)
        keyword: String,
    ): List<DeviceDTO>

    @GET(Constants.API_DEVICE_INDEX_URL)
    suspend fun getByStatus(
        @Query(Constants.API_DEVICE_KEY_STATUS)
        keyword: String,
    ): List<DeviceDTO>

    @GET(Constants.API_DEVICE_INDEX_URL)
    suspend fun getByManufacturer(
        @Query(Constants.API_DEVICE_KEY_OEM)
        keyword: String,
    ): List<DeviceDTO>

    @PUT(Constants.API_DEVICE_INDEX_URL + "{id}")
    suspend fun putDevice(
        @Path("id")
        id: String,
        @Body device: DeviceDTO
    ): Response<ResponseBody>

    @PATCH(Constants.API_DEVICE_INDEX_URL + "{id}")
    suspend fun updateDevice(
        @Path("id")
        id: String,
        @Body device: DeviceDTO
    ): Response<ResponseBody>
}