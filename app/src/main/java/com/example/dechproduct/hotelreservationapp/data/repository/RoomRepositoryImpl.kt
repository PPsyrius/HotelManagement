package com.example.dechproduct.hotelreservationapp.data.repository

import android.content.SharedPreferences
import android.util.Log
import com.example.dechproduct.hotelreservationapp.data.api.RoomAPIService
import com.example.dechproduct.hotelreservationapp.data.model.RoomDTO
import com.example.dechproduct.hotelreservationapp.data.model.Room
import com.example.dechproduct.hotelreservationapp.data.model.utility.booking.GuestStatus
import com.example.dechproduct.hotelreservationapp.data.model.utility.room.BedType
import com.example.dechproduct.hotelreservationapp.data.model.utility.room.Feature
import com.example.dechproduct.hotelreservationapp.data.model.utility.room.RoomStatus
import com.example.dechproduct.hotelreservationapp.data.model.utility.room.RoomType
import com.example.dechproduct.hotelreservationapp.domain.repository.RoomRepository
import com.example.dechproduct.hotelreservationapp.util.Resource
import javax.inject.Inject

class RoomRepositoryImpl @Inject constructor(
    private val roomAPI: RoomAPIService,
    val sharedPreferences: SharedPreferences
) : RoomRepository {

    override suspend fun editRoom(room: Room): Resource<Room> {
        return try {

            var response = room.roomID?.let { roomAPI.updateRoom(it, room) }

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

    override suspend fun getRoom(
        keyword: String,
        status: RoomStatus
    ): Resource<MutableList<Room>> {
        return try {

            var results: MutableList<Room> = mutableListOf<Room>()

            filterResult(roomAPI.getByID(keyword), results, status)

            Resource.Success(results)

        } catch (exception: IndexOutOfBoundsException) {
            Resource.Failure(Exception("No room(s) found."))
        } catch (exception: Exception) {
            Log.d("RoomRepositoryImpl", exception.toString())
            Resource.Failure(exception)
        }
    }

    //TODO:Cutoff unfulfilled reserved

    override suspend fun searchRoom(
        roomType: RoomType,
        bedType: BedType,
        features: List<Feature>,
        smoking: Boolean,
        status: RoomStatus
    ): Resource<MutableList<Room>> {
        return try {
            var results: MutableList<Room> = mutableListOf<Room>()

            filterResult(roomAPI.getByType(roomType.internalCode), results, status)
            filterResult(roomAPI.getByBed(bedType.internalCode), results, status)
            for (feature in features) {
                filterResult(roomAPI.getByFeature(feature.internalCode), results, status)
            }
            filterResult(roomAPI.getBySmoking(smoking), results, status)
            Resource.Success(results)

        } catch (exception: IndexOutOfBoundsException) {
            Resource.Failure(Exception("No room(s) found."))
        } catch (exception: Exception) {
            Log.d("RoomRepositoryImpl", exception.toString())
            Resource.Failure(exception)
        }
    }

    private fun filterResult(
        s_results: List<RoomDTO>,
        results: MutableList<Room>,
        status: RoomStatus = RoomStatus.NONE
    ) {
        for (result in s_results) {
            try {
                if (status == RoomStatus.NONE || status.internalCode == result.roomStatus) {
                    results.add(result.toRoom())
                }
            } catch (e: java.lang.Exception) {
            }
        }
    }

}
