package com.example.dechproduct.hotelreservationapp.data.repository

import android.content.SharedPreferences
import android.util.Log
import com.example.dechproduct.hotelreservationapp.data.api.RoomAPIService
import com.example.dechproduct.hotelreservationapp.data.model.Booking
import com.example.dechproduct.hotelreservationapp.data.model.Feature
import com.example.dechproduct.hotelreservationapp.data.model.Room
import com.example.dechproduct.hotelreservationapp.data.model.RoomStatus
import com.example.dechproduct.hotelreservationapp.domain.repository.RoomRepository
import com.example.dechproduct.hotelreservationapp.util.Constants
import com.example.dechproduct.hotelreservationapp.util.Resource
import javax.inject.Inject

class RoomRepositoryImpl @Inject constructor(
    private val roomAPI: RoomAPIService,
    val sharedPreferences: SharedPreferences
) : RoomRepository {

    override suspend fun editRoom(room: Room): Resource<Room> {
        return try {

            var response = room.roomID?.let{ roomAPI.updateRoom(it, room)}

            if (response!!.isSuccessful)
                Resource.Success(room)
            else
                throw Exception("An unknown error has occur.")
        } catch (exception: IndexOutOfBoundsException) {
            Resource.Failure(Exception("No room(s) found."))
        } catch (exception: Exception) {
            Log.d("RoomRepositoryImpl", exception.toString())
            Resource.Failure(exception)
        }
    }

    override suspend fun searchRoom(keyword: String): Resource<MutableList<Room>> {
        return try {

            var results: MutableList<Room> = mutableListOf<Room>()

            //TODO: Implements multiple ways to search (price range, type, floor)

            if (true)
                Resource.Success(results)
            else
                throw Exception("An unknown error has occur.")
        } catch (exception: IndexOutOfBoundsException) {
            Resource.Failure(Exception("No room(s) found."))
        } catch (exception: Exception) {
            Log.d("RoomRepositoryImpl", exception.toString())
            Resource.Failure(exception)
        }

    }

}
