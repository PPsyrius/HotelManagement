package com.example.dechproduct.hotelreservationapp.presentation.checkinWalk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.dechproduct.hotelreservationapp.R
import com.example.dechproduct.hotelreservationapp.databinding.ActivityChecinWalkInBinding
import com.example.dechproduct.hotelreservationapp.presentation.reservation.ReservationMenuActivity
import com.example.dechproduct.hotelreservationapp.presentation.reservation.add.camera.CameraActivity
import com.example.dechproduct.hotelreservationapp.presentation.roomBedBottomSheet.adapter.adapter.RoomBedBottomSheetFragment
import com.example.dechproduct.hotelreservationapp.presentation.roomTypeBottomSheet.RoomTypeBottomSheetFragment
import com.example.dechproduct.hotelreservationapp.util.Resource
import com.google.android.material.datepicker.MaterialDatePicker
import com.omarshehe.forminputkotlin.FormInputMultiline
import com.omarshehe.forminputkotlin.FormInputSpinner
import com.omarshehe.forminputkotlin.FormInputText
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


@AndroidEntryPoint
class CheckInWalkInActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChecinWalkInBinding
    private val checkInWalkInViewModel: CheckInWalkinViewModel by viewModels()

    var bottomSheetRoomBedFragment = RoomBedBottomSheetFragment()
    var bottomSheetChangeRoomTypeFragment = RoomTypeBottomSheetFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_checin_walk_in)

        binding.buttonTest.setOnClickListener {
            showDateRangePicker()
        }

        binding.btnSubmit.setOnClickListener {

            //TODO: Check no fields are blank -- TUNG
            var fname = findViewById<FormInputText>(R.id.first_name_customer).getValue()
            var lname = findViewById<FormInputText>(R.id.last_name_customer).getValue()
            var phone = findViewById<FormInputText>(R.id.phoneNumber).getValue()
            //TODO: Pass Payment Type object from here
            var payment = findViewById<FormInputSpinner>(R.id.payment_type).getValue()
            var id = findViewById<FormInputText>(R.id.ID).getValue()
            var address = findViewById<FormInputMultiline>(R.id.about).getValue()
            var adult_count = binding.edtGuestNumber.text.toString()
            var child_count = binding.edtChildNumber.text.toString()


            val adultIntFromET: Int = adult_count.toInt()
            val childIntFromET: Int = child_count.toInt()





            lifecycleScope.launch {
                checkInWalkInViewModel.addReserve(
                    fname, lname, phone,
                    payment, id, address, adultIntFromET, childIntFromET
                )

                Log.i("Addreser", adultIntFromET.toString())
                Log.i("Addreser2", childIntFromET.toString())
            }

            finish()

        }

        binding.buttonCamera.setOnClickListener {
            Toast.makeText(applicationContext, "Camera Button is Tapped.", Toast.LENGTH_LONG).show()
            val intent =
                Intent(this, CameraActivity::class.java)
            startActivity(intent)
        }

        binding.btnBackMenu.setOnClickListener {
            finish()
        }

        binding.btnPlus.setOnClickListener {
            lifecycleScope.launch {
                checkInWalkInViewModel.increment()
                binding.edtGuestNumber.setText(checkInWalkInViewModel.amount.value.toString())

            }

        }

        binding.btnMinus.setOnClickListener {
            lifecycleScope.launch {
                checkInWalkInViewModel.decrement()
                binding.edtGuestNumber.setText(checkInWalkInViewModel.amount.value.toString())
            }

        }

        binding.btnPlusChild.setOnClickListener {
            lifecycleScope.launch {
                checkInWalkInViewModel.incrementChild()
                binding.edtChildNumber.setText(checkInWalkInViewModel.amountChild.value.toString())
            }
        }

        binding.btnMinusChild.setOnClickListener {
            lifecycleScope.launch {
                checkInWalkInViewModel.decrementChild()
                binding.edtChildNumber.setText(checkInWalkInViewModel.amountChild.value.toString())
            }
        }

        binding.edtGuestNumber.setOnClickListener {
            Log.i("AddReservationActivity", "edittextGuest clicked")
        }


        binding.edtChildNumber.setOnClickListener {
            Log.i("AddReservationActivity", "edittextChild clicked")
        }

        binding.btnRoomType.setOnClickListener{
            bottomSheetChangeRoomTypeFragment.show(supportFragmentManager, "TAG")

        }

        binding.btnRoomBed.setOnClickListener {
            bottomSheetRoomBedFragment.show(supportFragmentManager, "TAG")

        }
        observeSubmit()
    }

    private fun showDateRangePicker() {
        val dateRangePicker = MaterialDatePicker.Builder
            .dateRangePicker()
            .setTitleText("Select Booking Date ")
            .build()

        dateRangePicker.show(
            supportFragmentManager,
            "date_range_picker"
        )

        dateRangePicker.addOnPositiveButtonClickListener { datePicked ->
            checkInWalkInViewModel.startDateEpoch = datePicked.first
            checkInWalkInViewModel.endDateEpoch = datePicked.second
            if (checkInWalkInViewModel.startDateEpoch != null && checkInWalkInViewModel.endDateEpoch != null) {
                binding.tvDateStart.text = convertLongToDate(checkInWalkInViewModel.startDateEpoch)
                binding.tvDateEnd.text = convertLongToDate(checkInWalkInViewModel.endDateEpoch)

            }
        }
    }

    private fun convertLongToDate(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat(
            "dd-MM-yyyy",
            Locale.getDefault()
        )
        return format.format(date)
    }

    private fun observeSubmit() {
        checkInWalkInViewModel.reserver.observe(this, {
            when (it) {
                is Resource.Success -> {
                    it.data?.let { _ ->
                        Toast.makeText(
                            applicationContext, "Booking Success.",
                            Toast.LENGTH_SHORT
                        ).show()
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