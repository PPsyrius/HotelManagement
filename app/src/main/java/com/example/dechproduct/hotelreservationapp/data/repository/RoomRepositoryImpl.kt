package com.example.dechproduct.hotelreservationapp.data.repository

import android.content.SharedPreferences
import android.util.Log
import com.example.dechproduct.hotelreservationapp.data.api.RoomAPIService
import com.example.dechproduct.hotelreservationapp.data.model.booking.Booking
import com.example.dechproduct.hotelreservationapp.data.model.booking.BookingStatus
import com.example.dechproduct.hotelreservationapp.data.model.room.*
import com.example.dechproduct.hotelreservationapp.domain.repository.RoomRepository
import com.example.dechproduct.hotelreservationapp.util.Resource
import java.io.IOException
import java.util.*
import javax.inject.Inject

class RoomRepositoryImpl @Inject constructor(
    private val roomAPI: RoomAPIService,
    val sharedPreferences: SharedPreferences
) : RoomRepository {

    override suspend fun editRoom(room: Room): Resource<Room> {
        return try {

            var response = room.roomID?.let { roomAPI.updateRoom(it, room.toRoomDTO()) }

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

    override suspend fun searchRoomByID(
        keyword: String
    ): Resource<MutableList<Room>> {
        return try {
            var results: MutableList<Room> = toListOfRooms(roomAPI.getByRoomID(keyword))
            results = filterStatus(results)

            Resource.Success(results)

        } catch (exception: IndexOutOfBoundsException) {
            Resource.Failure(Exception("No room(s) found."))
        } catch (exception: Exception) {
            Log.d("RoomRepositoryImpl", exception.toString())
            Resource.Failure(exception)
        }
    }

    override suspend fun getRoomByID(
        keyword: String
    ): Resource<Room> {
        return try {
            Resource.Success(roomAPI.getByID(keyword).toRoom())

        } catch (exception: Exception) {
            Log.d("RoomRepositoryImpl", exception.toString())
            Resource.Failure(exception)
        }
    }

    override suspend fun searchRoom(
        keyword: String,
        roomType: RoomType,
        bedType: BedType,
        features: List<Feature>,
        smoking: Boolean,
        status: List<RoomStatus>,
        occupancy: Occupancy?
    ): Resource<MutableList<Room>> {
        return try {
            var results: MutableList<Room> = toListOfRooms(roomAPI.getByType(roomType.internalCode))
            combineRoom(results, toListOfRooms(roomAPI.getByBed(bedType.internalCode)))
            for (feature in features) {
                combineRoom(results, toListOfRooms(roomAPI.getByFeature(feature.internalCode)))
            }
            combineRoom(results, toListOfRooms(roomAPI.getBySmoking(smoking)))
            // Query keywords empty -> query_like=""
            combineRoom(results, toListOfRooms(roomAPI.getByRoomID(keyword)))

            results = filterStatus(results, status)
            occupancy?.let {
                results = filterAvailableDate(results, occupancy)

            }

            Resource.Success(results)

        } catch (exception: IndexOutOfBoundsException) {
            Resource.Failure(Exception("No room(s) found."))
        } catch (exception: Exception) {
            Log.d("RoomRepositoryImpl", exception.toString())
            Resource.Failure(exception)
        }
    }

    override suspend fun populate(status: List<RoomStatus>): Resource<MutableList<Room>> {
        return try {
            var result_default = roomAPI.getAll()
            var results: MutableList<Room> = toListOfRooms(result_default)
            results = filterStatus(results)

            Resource.Success(results)
        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }

    private fun combineRoom(
        rooms_target: MutableList<Room>,
        rooms_part: MutableList<Room>
    ) {
        for (item in rooms_target) {
            if (!rooms_part.contains(item)) {
                rooms_target.remove(item)
            }
        }
    }

    private fun toListOfRooms(raw_results: List<RoomDTO>): MutableList<Room> {
        var results: MutableList<Room> = mutableListOf<Room>()
        for (result in raw_results) {
            try {
                results.add(result.toRoom())
            } catch (e: Exception) {
            }
        }
        return results
    }

    private fun filterAvailableDate(
        raw_results: List<Room>,
        occupancy: Occupancy
    ): MutableList<Room> {
        var results: MutableList<Room> = mutableListOf<Room>()
        var _ignore: Boolean = false

        for (result in raw_results) {
            try {
                if (result.occupancy.isNullOrEmpty()) {
                    results.add(result)
                    continue
                } else if (result.status == RoomStatus.GUARANTEED) {
                    continue
                }

                for (range in result.occupancy!!) {
                    if (occupancy.arrivalDate == range.arrivalDate && occupancy.departDate == range.departDate) {
                        _ignore = false
                        break
                    } else if (occupancy.arrivalDate!!.before(range.arrivalDate) &&
                        occupancy.departDate!!.after(range.arrivalDate)
                    ) {
                        _ignore = true
                        break
                    } else if (occupancy.arrivalDate!!.before(range.departDate)) {
                        _ignore = true
                        break
                    }
                }
                if (!_ignore) {
                    results.add(result)
                }
                _ignore = false
            } catch (e: Exception) {
            }
        }
        return results
    }

    private fun filterStatus(
        raw_results: List<Room>,
        status: List<RoomStatus> = mutableListOf<RoomStatus>(RoomStatus.READY)
    ): MutableList<Room> {
        var results: MutableList<Room> = mutableListOf<Room>()
        for (result in raw_results) {
            try {
                if (status.contains(result.status)) {
                    results.add(result)
                }
            } catch (e: Exception) {
                Log.d("FilterRoomStatus:", e.toString())
            }
        }
        return results
    }

}
