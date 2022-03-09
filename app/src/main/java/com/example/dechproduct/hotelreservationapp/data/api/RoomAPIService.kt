package com.example.dechproduct.hotelreservationapp.data.api

import com.example.dechproduct.hotelreservationapp.data.model.Booking
import com.example.dechproduct.hotelreservationapp.data.model.BookingDTO
import com.example.dechproduct.hotelreservationapp.util.Constants
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface RoomAPIService {

    @GET(Constants.API_ROOM_INDEX_URL)
    suspend fun getByID(
        @Query(Constants.API_ROOM_KEY_ID)
        id: String,
    ): List<BookingDTO>

    @GET(Constants.API_ROOM_INDEX_URL)
    suspend fun getByType(
        @Query(Constants.API_ROOM_KEY_TYPE)
        type:String,
    ): List<BookingDTO>

    @GET(Constants.API_ROOM_INDEX_URL)
    suspend fun getByBed(
        @Query(Constants.API_ROOM_KEY_BEDS)
        bed:String,
    ): List<BookingDTO>

    @GET(Constants.API_ROOM_INDEX_URL)
    suspend fun getByCapacity(
        @Query(Constants.API_ROOM_KEY_CAP)
        capacity:String,
    ): List<BookingDTO>

    @GET(Constants.API_ROOM_INDEX_URL)
    suspend fun getByFloor(
        @Query(Constants.API_ROOM_KEY_FLOOR)
        floor:String,
    ): List<BookingDTO>

    @GET(Constants.API_ROOM_INDEX_URL)
    suspend fun getByMap(
        @Query(Constants.API_ROOM_KEY_MAP)
        mapping:String,
    ): List<BookingDTO>

    @GET(Constants.API_ROOM_INDEX_URL)
    suspend fun getByStatus(
        @Query(Constants.API_ROOM_KEY_STAT)
        status:String,
    ): List<BookingDTO>

    //For Non-CheckIn purposes

    @GET(Constants.API_ROOM_INDEX_URL)
    suspend fun getByIsWalking(
        @Query(Constants.API_ROOM_KEY_WALK)
        walking:String,
    ): List<BookingDTO>

    /* UNUSED; If server side doesn't accept queries

    @GET(Constants.API_ROOM_INDEX_URL)
    suspend fun getByPriceRange(
        @Query(Constants.API_ROOM_KEY_PRICE)
        Price:String, toPrice:String,
    ): List<BookingDTO>
     */

    //Use this function instead for complex queries (e.g. Range queries)

    @GET(Constants.API_ROOM_INDEX_URL)
    suspend fun getAll(): List<BookingDTO>

    /* UNUSED; Shouldn't be able to add/modify room from kiosks

    @POST(Constants.API_ROOM_INDEX_URL)
    suspend fun postRoom(
        @Body room: Room,
    ): Response<ResponseBody>

    @PUT(Constants.API_ROOM_INDEX_URL)
    suspend fun putRoom(
        @Query(Constants.API_ROOM_KEY_ID)
        id:String,
        @Body booking:Booking
    ): Response<ResponseBody>

    @PATCH(Constants.API_BOOK_INDEX_URL)
    suspend fun updateRoom(
        @Query(Constants.API_BOOK_KEY_ID)
        id:String,
        @Body booking:Booking
    ): Response<ResponseBody>


    @DELETE(Constants.API_BOOK_INDEX_URL)
    suspend fun deleteRoom(
        @Query(Constants.API_BOOK_KEY_ID)
        id:String,
    ): Response<ResponseBody>

     */

}