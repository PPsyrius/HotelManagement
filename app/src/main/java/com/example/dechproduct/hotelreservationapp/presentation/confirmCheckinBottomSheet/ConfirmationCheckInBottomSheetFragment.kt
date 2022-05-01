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
import androidx.fragment.app.viewModels

import com.example.dechproduct.hotelreservationapp.presentation.checkin.CheckInActivity
import com.example.dechproduct.hotelreservationapp.presentation.checkinWalk.CheckInWalkinViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ConfirmationCheckInBottomSheetFragment : BottomSheetDialogFragment() {

    //TODO: Handle Layout to Responsive --TUNG

    private lateinit var binding : FragmentConfirmationCheckInBottomSheetBinding
    private val confirmationCheckInBottomSheetViewModel: ConfirmationCheckInBottomSheetViewModel by viewModels()


    private lateinit var dialog: BottomSheetDialog
    private lateinit var behavior: BottomSheetBehavior<View>

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



        binding.btnBackCheckInDetail.setOnClickListener {
            dismiss()
        }

        binding.tvDisplayGuestName.text = "Marry Jane"

        binding.tvAdultCount.text = "2"

        binding.tvChildCount.text = "1"

        binding.tvDateCheckInConfirmation.text = "01-02-2023"

        binding.tvCheckOutDateConfirmation.text = "02-03-2024"

        binding.tvConfirmRoomNumber.text = "102"

        binding.tvConfirmRoomBed.text = "Single Bed"

        binding.tvConfirmRoomType.text = "Suite"

        binding.tvConfirmPrice.text = "12,000"

        binding.cbBreakfastConfirmCheckIn.isChecked = true

        binding.cbSmokeConfirmCheckIn.isChecked = false









        binding.btnConfirmCheckInDetail.setOnClickListener {
            Toast.makeText(context, "Confirmation Clicked", Toast.LENGTH_LONG)
                .show()
            val intent = Intent(context, CheckInActivity::class.java)
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
                behaviour.peekHeight =BottomSheetBehavior.PEEK_HEIGHT_AUTO
            }
        }
        return dialog
    }







}