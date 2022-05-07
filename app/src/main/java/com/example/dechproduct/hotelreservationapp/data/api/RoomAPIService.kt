package com.example.dechproduct.hotelreservationapp.data.api

import com.example.dechproduct.hotelreservationapp.data.model.room.Room
import com.example.dechproduct.hotelreservationapp.data.model.room.RoomDTO
import com.example.dechproduct.hotelreservationapp.util.Constants
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface RoomAPIService {

    @GET(Constants.API_ROOM_INDEX_URL)
    suspend fun getByID(
        @Query(Constants.API_ROOM_KEY_ID)
        id: String,
    ): List<RoomDTO>

    @GET(Constants.API_ROOM_INDEX_URL)
    suspend fun getByType(
        @Query(Constants.API_ROOM_KEY_TYPE)
        type:String,
    ): List<RoomDTO>

    @GET(Constants.API_ROOM_INDEX_URL)
    suspend fun getByBed(
        @Query(Constants.API_ROOM_KEY_BEDS)
        bed:String,
    ): List<RoomDTO>

    @GET(Constants.API_ROOM_INDEX_URL)
    suspend fun getByCapacity(
        @Query(Constants.API_ROOM_KEY_CAP)
        capacity:String,
    ): List<RoomDTO>

    @GET(Constants.API_ROOM_INDEX_URL)
    suspend fun getByFloor(
        @Query(Constants.API_ROOM_KEY_FLOOR)
        floor:String,
    ): List<RoomDTO>

    @GET(Constants.API_ROOM_INDEX_URL)
    suspend fun getByFeature(
        @Query(Constants.API_ROOM_KEY_FEATURES + Constants.JSON_SERVER_FILTER)
        feature:String,
    ): List<RoomDTO>

    @GET(Constants.API_ROOM_INDEX_URL)
    suspend fun getBySmoking(
        @Query(Constants.API_ROOM_KEY_SMOKING)
        smoking:Boolean,
    ): List<RoomDTO>

    @GET(Constants.API_ROOM_INDEX_URL)
    suspend fun getByStatus(
        @Query(Constants.API_ROOM_KEY_STATUS)
        status:String,
    ): List<RoomDTO>

    @GET(Constants.API_ROOM_INDEX_URL)
    suspend fun getByIsWalking(
        @Query(Constants.API_ROOM_KEY_WALKING)
        walking:Boolean,
    ): List<RoomDTO>

    @GET(Constants.API_ROOM_INDEX_URL)
    suspend fun getAll(): List<RoomDTO>

    @POST(Constants.API_ROOM_INDEX_URL)
    suspend fun postRoom(
        @Body room: Room,
    ): Response<ResponseBody>

    @PUT(Constants.API_ROOM_INDEX_URL + "{id}")
    suspend fun putRoom(
        @Path("id")
        id:String,
        @Body room: Room
    ): Response<ResponseBody>

    @PATCH(Constants.API_ROOM_INDEX_URL + "{id}")
    suspend fun updateRoom(
        @Path("id")
        id:String,
        @Body room: Room
    ): Response<ResponseBody>

    @DELETE(Constants.API_ROOM_INDEX_URL)
    suspend fun deleteRoom(
        @Query(Constants.API_ROOM_KEY_ID)
        id:String,
    ): Response<ResponseBody>
}