package com.example.dechproduct.hotelreservationapp.domain.usecase

import com.example.dechproduct.hotelreservationapp.domain.usecase.checkin.*
import com.example.dechproduct.hotelreservationapp.domain.usecase.checkout.CheckOutGuestUseCase
import com.example.dechproduct.hotelreservationapp.domain.usecase.checkout.ExtendStayUseCase
import com.example.dechproduct.hotelreservationapp.domain.usecase.checkout.SearchGuestUseCase
import com.example.dechproduct.hotelreservationapp.domain.usecase.information.ShowPromotionUseCase
import com.example.dechproduct.hotelreservationapp.domain.usecase.auth.*
import com.example.dechproduct.hotelreservationapp.domain.usecase.reservation.*
import com.example.dechproduct.hotelreservationapp.domain.usecase.room.*
import com.example.dechproduct.hotelreservationapp.domain.usecase.auth.LogoutUseCase
import com.example.dechproduct.hotelreservationapp.domain.usecase.com.GetReserveByIDUseCase

class UseCase(
    var loginUseCase: LoginUseCase,
    var logoutUseCase: LogoutUseCase,

    var checkInFromReservationUseCase: CheckInFromReservationUseCase,
    var checkInFromWalkInUseCase: CheckInFromWalkInUseCase,

    var checkOutGuestUseCase: CheckOutGuestUseCase,
    var extendStayUseCase: ExtendStayUseCase,
    var searchGuestUseCase: SearchGuestUseCase,

    var getReserveByIDUseCase: GetReserveByIDUseCase,

    var showPromotionUseCase: ShowPromotionUseCase,

    var addReserveUseCase: AddReserveUseCase,
    var editReserveUseCase: EditReserveUseCase,
    var populateReserveUseCase: PopulateReserveUseCase,
    var removeReserveUseCase: RemoveReserveUseCase,
    var searchReserveByIDUseCase: SearchReserveByIDUseCase,
    var searchReserveByNameUseCase: SearchReserveByNameUseCase,

    var markRoomUseCase: MarkRoomUseCase,
    var searchRoomUseCase: SearchRoomUseCase,
    ){}
