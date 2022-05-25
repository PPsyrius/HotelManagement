package com.example.dechproduct.hotelreservationapp.presentation.confirmCheckinBottomSheet

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dechproduct.hotelreservationapp.databinding.FragmentConfirmationCheckInBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.example.dechproduct.hotelreservationapp.R


import android.content.Intent
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels

import com.example.dechproduct.hotelreservationapp.presentation.checkin.CheckInActivity
import com.example.dechproduct.hotelreservationapp.presentation.checkin.CheckInViewModel
import com.example.dechproduct.hotelreservationapp.presentation.checkinDetail.CheckinDetailViewModel
import com.example.dechproduct.hotelreservationapp.presentation.menu.MenuActivity
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat


@AndroidEntryPoint
class ConfirmationCheckInBottomSheetFragment : BottomSheetDialogFragment() {

    //TODO: Handle Layout to Responsive --TUNG

    private lateinit var binding: FragmentConfirmationCheckInBottomSheetBinding
    private val confirmationCheckInBottomSheetViewModel: ConfirmationCheckInBottomSheetViewModel by viewModels()
    private val checkInDetailViewModel: CheckinDetailViewModel by activityViewModels()
    private val checkInViewModel: CheckInViewModel by activityViewModels()

    private lateinit var dialog: BottomSheetDialog
    private lateinit var behavior: BottomSheetBehavior<View>

    private val dateFormat = SimpleDateFormat("dd-MM-yyyy")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_confirmation_check_in_bottom_sheet,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentConfirmationCheckInBottomSheetBinding.bind(view)
        Toast.makeText(context, checkInDetailViewModel.breakfast.toString(), Toast.LENGTH_LONG)
            .show()
        binding.btnBackCheckInDetail.setOnClickListener {
            activity?.finish()
        }

        binding.tvDisplayGuestName.text =
            checkInDetailViewModel.reservation.guest?.firstName +
                    " " + checkInDetailViewModel.reservation.guest?.lastName

        binding.tvAdultCount.text = checkInDetailViewModel.reservation.adultCount.toString()

        binding.tvChildCount.text = checkInDetailViewModel.reservation.childCount.toString()

        binding.tvDateCheckInConfirmation.text =
            dateFormat.format(checkInDetailViewModel.reservation.arrivalDate)

        binding.tvCheckOutDateConfirmation.text =
            dateFormat.format(checkInDetailViewModel.reservation.departDate)

        binding.tvConfirmRoomNumber.text = checkInDetailViewModel.selectedRoom.roomID

        binding.tvConfirmRoomBed.text = checkInDetailViewModel.selectedRoom.beds.toString()

        binding.tvConfirmRoomType.text = checkInDetailViewModel.selectedRoom.type.toString()

        binding.tvConfirmPrice.text = checkInDetailViewModel.selectedRoom.price.toString()

        binding.cbBreakfastConfirmCheckIn.isChecked =checkInDetailViewModel.breakfast

        binding.cbSmokeConfirmCheckIn.isChecked = checkInDetailViewModel.selectedRoom.smoking == true

        binding.btnConfirmCheckInDetail.setOnClickListener {
            checkInDetailViewModel.checkInReserved()

            checkInViewModel.refreshCall.postValue(checkInDetailViewModel.reservation.status)
            val intent = Intent(context, MenuActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setOnShowListener {
            val bottomSheetDialog = it as BottomSheetDialog
            val parentLayout = bottomSheetDialog.findViewById<View>(
                com.google.android.material.R.id.design_bottom_sheet
            )
            parentLayout?.let { bottomSheet ->
                val behaviour = BottomSheetBehavior.from(bottomSheet)
                val layoutParams = bottomSheet.layoutParams
                layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
                bottomSheet.layoutParams = layoutParams
                behaviour.state = BottomSheetBehavior.STATE_EXPANDED
                behaviour.peekHeight = BottomSheetBehavior.PEEK_HEIGHT_AUTO
            }
        }
        return dialog
    }


}