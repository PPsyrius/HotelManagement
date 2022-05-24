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

    var checkInGuestUseCase: CheckInGuestUseCase,

    var checkOutGuestUseCase: CheckOutGuestUseCase,
    var extendStayUseCase: ExtendStayUseCase,
    var searchGuestUseCase: SearchGuestUseCase,

    var getReserveByIDUseCase: GetReserveByIDUseCase,

    var showPromotionUseCase: ShowPromotionUseCase,

    var addReserveUseCase: AddReserveUseCase,
    var cancelReserveUseCase: CancelReserveUseCase,
    var editReserveUseCase: EditReserveUseCase,
    var populateReserveUseCase: PopulateReserveUseCase,
    var removeReserveUseCase: RemoveReserveUseCase,
    var searchReserveByIDUseCase: SearchReserveByIDUseCase,
    var searchReserveByNameUseCase: SearchReserveByNameUseCase,
    var searchReserveByArrivalUseCase: SearchReserveByArrivalUseCase,
    var searchReserveByDepartUseCase: SearchReserveByDepartUseCase,
    var searchReserveByNamedArrivalUseCase: SearchReserveByNamedArrivalUseCase,
    var searchReserveByNamedDepartUseCase: SearchReserveByNamedDepartUseCase,

    var editRoomUseCase: EditRoomUseCase,
    var markRoomUseCase: MarkRoomUseCase,
    var getRoomUseCase: GetRoomUseCase,
    var searchRoomUseCase: SearchRoomUseCase,
    var populateRoomUseCase: PopulateRoomUseCase,
) {}
