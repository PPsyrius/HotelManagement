package com.example.dechproduct.hotelreservationapp.domain.usecase

import com.example.dechproduct.hotelreservationapp.domain.usecase.login.*
import com.example.dechproduct.hotelreservationapp.domain.usecase.reservation.*
import com.example.dechproduct.hotelreservationapp.domain.usecase.room.*

class UseCase(
    var loginUseCase: LoginUseCase,

    var addReserveUseCase: AddReserveUseCase,
    var searchReserveUseCase: SearchReserveUseCase,
    var populateReserveUseCase: PopulateReserveUseCase,
    var editReserveUseCase: EditReserveUseCase,
    var removeReserveUseCase: RemoveReserveUseCase,
    var searchRoomUseCase: SearchRoomUseCase,
    var markRoomUseCase: MarkRoomUseCase


    ){}
