package com.example.dechproduct.hotelreservationapp.data.repository

import android.content.SharedPreferences
import android.util.Log
import com.example.dechproduct.hotelreservationapp.data.api.RoomAPIService
import com.example.dechproduct.hotelreservationapp.data.model.room.*
import com.example.dechproduct.hotelreservationapp.domain.repository.RoomRepository
import com.example.dechproduct.hotelreservationapp.util.Resource
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
        occupancy: Occupancy?,
        adult_count: Int,
        child_count: Int
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

            //Straight Forward Query
            //combineRoom(results, toListOfRooms(roomAPI.getByCapacity(adult_count.toString())))

            //Non-Supported by API
            for (room in results) {
                if (room.maxCap ?: -1 < adult_count) {
                    results.remove(room)
                }
            }

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

        for (result in raw_results) {

            var _ignore: Boolean = false

            try {
                if (result.occupancy.isNullOrEmpty()) {
                    results.add(result)
                    continue
                }
                //Unused for now, but no effects on process.
                else if (result.status == RoomStatus.GUARANTEED) {
                    continue
                }

                for (occupied in result.occupancy!!) {
                    if (occupancy.arrivalDate == occupied.arrivalDate ||
                        occupancy.departDate == occupied.departDate
                    ) {
                        _ignore = true
                        break
                    }
                    else if (occupancy.arrivalDate!!.before(occupied.arrivalDate) &&
                        occupancy.departDate!!.after(occupied.arrivalDate)
                    ) {
                        _ignore = true
                        break
                    } else if (occupancy.arrivalDate!!.after(occupied.arrivalDate) &&
                        occupancy.departDate!!.before(occupied.departDate)
                    ) {
                        _ignore = true
                        break
                    } else if (occupancy.arrivalDate!!.before(occupied.departDate) &&
                        occupancy.departDate!!.after(occupied.departDate)
                    ) {
                        _ignore = true
                        break
                    }
                }
                if (!_ignore) {
                    results.add(result)
                }
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
