package com.example.dechproduct.hotelreservationapp.presentation.checkinDetail

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.dechproduct.hotelreservationapp.data.model.booking.Booking
import com.example.dechproduct.hotelreservationapp.data.model.room.BedType
import com.example.dechproduct.hotelreservationapp.data.model.room.RoomStatus
import com.example.dechproduct.hotelreservationapp.data.model.room.RoomType
import com.example.dechproduct.hotelreservationapp.databinding.ActivityCheckinDetailBinding
import com.example.dechproduct.hotelreservationapp.presentation.confirmCheckinBottomSheet.ConfirmationCheckInBottomSheetFragment
import com.example.dechproduct.hotelreservationapp.presentation.menu.MenuActivity
import com.example.dechproduct.hotelreservationapp.presentation.roomAvailableBottomSheet.RoomAvailableBottomSheetFragment
import com.example.dechproduct.hotelreservationapp.presentation.roomBedBottomSheet.adapter.adapter.RoomBedBottomSheetFragment
import com.example.dechproduct.hotelreservationapp.presentation.roomTypeBottomSheet.RoomTypeBottomSheetFragment
import com.example.dechproduct.hotelreservationapp.util.Constants
import com.example.dechproduct.hotelreservationapp.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class CheckInDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheckinDetailBinding    // <- can click here to open the xml that related
    private val checkInDetailViewModel: CheckinDetailViewModel by viewModels()

    private lateinit var selectedItem: Booking

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    var bottomSheetChangeRoomTypeFragment = RoomTypeBottomSheetFragment()

    var bottomSheetCheckingRoomAvailableFragment = RoomAvailableBottomSheetFragment()

    var bottomSheetRoomBedFragment = RoomBedBottomSheetFragment()

    var bottomSheetConfirmationFragment = ConfirmationCheckInBottomSheetFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckinDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBackMenu.setOnClickListener {
            finish()
        }

        binding.tvEditRoomType.setOnClickListener {
            bottomSheetChangeRoomTypeFragment.show(supportFragmentManager, "TAG")
        }

        binding.btnPlus.setOnClickListener {
            lifecycleScope.launch {
                checkInDetailViewModel.increment()
                binding.edtGuestNumber.setText(checkInDetailViewModel.amount.value.toString())

            }

        }

        binding.btnMinus.setOnClickListener {
            lifecycleScope.launch {
                checkInDetailViewModel.decrement()
                binding.edtGuestNumber.setText(checkInDetailViewModel.amount.value.toString())
            }

        }

        binding.btnPlusChild.setOnClickListener {
            lifecycleScope.launch {
                checkInDetailViewModel.incrementChild()
                binding.edtChildNumber.setText(checkInDetailViewModel.amountChild.value.toString())
            }
        }

        binding.btnMinusChild.setOnClickListener {
            lifecycleScope.launch {
                checkInDetailViewModel.decrementChild()
                binding.edtChildNumber.setText(checkInDetailViewModel.amountChild.value.toString())
            }
        }


        binding.edtGuestNumber.setOnClickListener {
            Log.i("CheckInDetailActivity", "edittextGuest clicked")
        }


        binding.edtChildNumber.setOnClickListener {
            Log.i("CheckInDetailActivity", "edittextChild clicked")
        }

        binding.tvCheckingRoom.setOnClickListener {
            try {
                Log.d("", checkInDetailViewModel.roomConfig.toString())
                bottomSheetCheckingRoomAvailableFragment.show(supportFragmentManager, "TAG")
                bottomSheetCheckingRoomAvailableFragment.onResume()
            } catch (e: Exception) {
                Log.d("ERR", e.toString())
            }
        }

        binding.tvSelectRoomBed.setOnClickListener {
            bottomSheetRoomBedFragment.show(supportFragmentManager, "TAG")
        }

        binding.btnConfirmationCheckIn.setOnClickListener {
            bottomSheetConfirmationFragment.show(supportFragmentManager, "TAG")
        }

        binding.cbBreakfast.setOnClickListener {
            Toast.makeText(applicationContext, "checkbox breakfast  clicked", Toast.LENGTH_LONG)
                .show()

        }

        binding.cbSmoking.setOnClickListener {
            Toast.makeText(applicationContext, "checkbox smoking  clicked", Toast.LENGTH_LONG)
                .show()

        }
        receiveSelected()
        observeUpdateInfo()
        observeCheckInResolve()
    }

    private fun receiveSelected() {
        try {
            selectedItem = intent.getParcelableExtra<Booking>(Constants.INTENT_SELECTED_BOOKING)!!

            selectedItem.bookingID?.let { checkInDetailViewModel.updateInfo(it) }

        } catch (e: Exception) {
        }
    }

    private fun observeUpdateInfo() {
        checkInDetailViewModel.selectedReservation.observe(this, {
            when (it) {
                is Resource.Success -> {
                    it.data?.let { reservation ->
                        checkInDetailViewModel.reservation = reservation
                        checkInDetailViewModel.roomConfig.beds = reservation.room?.beds
                        checkInDetailViewModel.roomConfig.type = reservation.room?.type
                        checkInDetailViewModel.roomConfig.smoking = reservation.room?.smoking

                        checkInDetailViewModel.selectedRoom = reservation.room!!

                        binding.tvGuestName.text =
                            reservation.guest?.firstName + " " + reservation.guest?.lastName
                        binding.roomType.text = reservation.room?.type.toString()
                        if (binding.roomType.text == null) {
                            binding.roomType.text = ""
                        }
                        binding.tvCheckInDate.text = SimpleDateFormat(
                            "dd-MM-yyyy",
                            Locale.getDefault()
                        ).format(reservation.arrivalDate)
                        binding.tvCheckOutDate.text = SimpleDateFormat(
                            "dd-MM-yyyy",
                            Locale.getDefault()
                        ).format(reservation.departDate)
                        binding.tvDisplayRoomNumber.text = reservation.room?.roomID
                        binding.tvDisplayRoomBed.text = reservation.room?.beds.toString()
                        if (binding.tvDisplayRoomBed.text == null) {
                            binding.tvDisplayRoomBed.text = ""
                        }

                        binding.edtGuestNumber.setText(reservation.adultCount?.toString())
                        binding.edtChildNumber.setText(reservation.childCount?.toString())

                        binding.cbBreakfast.isChecked = reservation.breakfast == true
                        binding.cbSmoking.isChecked = reservation.room?.smoking == true
                    }
                }
                is Resource.Failure -> {
                    Toast.makeText(applicationContext, it.throwable.message, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
    }

    private fun observeCheckInResolve() {
        checkInDetailViewModel.resolveReservation.observe(this, {
            when (it) {
                is Resource.Success -> {
                    it.data?.let { reservation ->
                        Log.d("CheckInResActivity", reservation.toString())
                        Toast.makeText(
                            applicationContext,
                            "Check-In Successful!",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }
                is Resource.Failure -> {
                    Toast.makeText(applicationContext, it.throwable.message, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
    }
}
