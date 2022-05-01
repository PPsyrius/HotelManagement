package com.example.dechproduct.hotelreservationapp.presentation.confirmCheckinBottomSheet

import androidx.lifecycle.ViewModel
import com.example.dechproduct.hotelreservationapp.domain.usecase.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel

class ConfirmationCheckInBottomSheetViewModel @Inject constructor(private val useCase: UseCase): ViewModel(){


}