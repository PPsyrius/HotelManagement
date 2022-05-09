package com.example.dechproduct.hotelreservationapp.presentation.roomAvailableBottomSheet

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dechproduct.hotelreservationapp.data.model.room.*
import com.example.dechproduct.hotelreservationapp.domain.usecase.UseCase
import com.example.dechproduct.hotelreservationapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomAvailableBottomSheetViewModel @Inject constructor(private val useCase: UseCase): ViewModel(){

    var roomer = MutableLiveData<Resource<MutableList<Room>>>()

    suspend fun getRoom(keyword:String){
        viewModelScope.launch {
            val rooms = useCase.getRoomUseCase(keyword)
            roomer.postValue(rooms)
        }
    }

    suspend fun searchRoom(
        keyword: String="",
        roomType: RoomType,
        bedType: BedType,
        features: List<Feature> = listOf<Feature>(),
        smoking: Boolean,
        roomStatus: List<RoomStatus> = listOf<RoomStatus>(),
        occupancy: Occupancy?
    ){
        viewModelScope.launch {
            val rooms = useCase.searchRoomUseCase(
                keyword = keyword,
                roomType = roomType,
                bedType = bedType,
                features = features,
                smoking = smoking,
                status = roomStatus,
                occupancy = occupancy
            )
            roomer.postValue(rooms)
        }
    }
}