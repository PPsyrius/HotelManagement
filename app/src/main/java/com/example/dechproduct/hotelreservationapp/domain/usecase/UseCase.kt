package com.example.dechproduct.hotelreservationapp.domain.usecase

import com.example.dechproduct.hotelreservationapp.domain.usecase.checkin.*
import com.example.dechproduct.hotelreservationapp.domain.usecase.checkout.CheckOutGuestUseCase
import com.example.dechproduct.hotelreservationapp.domain.usecase.checkout.ExtendStayUseCase
import com.example.dechproduct.hotelreservationapp.domain.usecase.checkout.SearchGuestUseCase
import com.example.dechproduct.hotelreservationapp.domain.usecase.information.ShowPromotionUseCase
import com.example.dechproduct.hotelreservationapp.domain.usecase.login.*
import com.example.dechproduct.hotelreservationapp.domain.usecase.reservation.*
import com.example.dechproduct.hotelreservationapp.domain.usecase.room.*
import com.example.dechproduct.hotelreservationapp.domain.usecase.utility.LogoutUseCase

class UseCase(
    var checkInFromReservationUseCase: CheckInFromReservationUseCase,
    var checkInFromWalkInUseCase: CheckInFromWalkInUseCase,

    var checkOutGuestUseCase: CheckOutGuestUseCase,
    var extendStayUseCase: ExtendStayUseCase,
    var searchGuestUseCase: SearchGuestUseCase,

    var showPromotionUseCase: ShowPromotionUseCase,

    var loginUseCase: LoginUseCase,

    var addReserveUseCase: AddReserveUseCase,
    var searchReserveUseCase: SearchReserveUseCase,
    var populateReserveUseCase: PopulateReserveUseCase,
    var editReserveUseCase: EditReserveUseCase,
    var removeReserveUseCase: RemoveReserveUseCase,

    var markRoomUseCase: MarkRoomUseCase,
    var searchRoomUseCase: SearchRoomUseCase,

    var logoutUseCase: LogoutUseCase,
    ){}
