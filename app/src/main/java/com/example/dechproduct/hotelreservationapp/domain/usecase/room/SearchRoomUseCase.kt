package com.example.dechproduct.hotelreservationapp.domain.usecase.room

import com.example.dechproduct.hotelreservationapp.data.model.room.*
import com.example.dechproduct.hotelreservationapp.domain.repository.RoomRepository
import com.example.dechproduct.hotelreservationapp.util.Resource
import java.util.*
import javax.inject.Inject

class SearchRoomUseCase @Inject constructor(private val roomRepository: RoomRepository) {
    suspend operator fun invoke(
        keyword: String = "",
        roomType: RoomType,
        bedType: BedType,
        features: List<Feature> = listOf<Feature>(),
        smoking: Boolean,
        status: List<RoomStatus> = listOf<RoomStatus>(),
        occupancy: Occupancy,
        adult_count: Int,
        child_count: Int
    ): Resource<MutableList<Room>> {
        return roomRepository.searchRoom(
            keyword = keyword,
            roomType = roomType,
            bedType = bedType,
            features = features,
            smoking = smoking,
            status = status,
            occupancy = occupancy,
            adult_count = adult_count,
            child_count = child_count
        )
    }
}