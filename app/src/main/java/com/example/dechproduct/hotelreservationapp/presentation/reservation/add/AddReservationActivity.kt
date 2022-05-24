package com.example.dechproduct.hotelreservationapp.presentation.reservation.add

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.dechproduct.hotelreservationapp.data.model.booking.Booking
import com.example.dechproduct.hotelreservationapp.data.model.payment.PaymentType
import com.example.dechproduct.hotelreservationapp.databinding.ActivityAddReservationBinding
import com.example.dechproduct.hotelreservationapp.presentation.addReservationRoomBedBottomSheet.AddReservationRoomBedBottomSheetFragment
import com.example.dechproduct.hotelreservationapp.presentation.addReservationRoomTypeBottomSheet.AddReservationRoomTypeBottomSheetFragment
import com.example.dechproduct.hotelreservationapp.presentation.checkin.CheckInActivity
import com.example.dechproduct.hotelreservationapp.presentation.reservation.ReservationMenuActivity
import com.example.dechproduct.hotelreservationapp.presentation.reservation.add.camera.CameraFragment
import com.example.dechproduct.hotelreservationapp.presentation.reservation.search.SearchReservationActivity
import com.example.dechproduct.hotelreservationapp.util.Constants
import com.example.dechproduct.hotelreservationapp.util.Resource
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import android.content.DialogInterface

import androidx.appcompat.app.AlertDialog


@AndroidEntryPoint
class AddReservationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddReservationBinding
    private val addReservationViewModel: AddReservationViewModel by viewModels()
    //TODO: ID Verification, and phone number filter needs improvement

    var bottomSheetRoomBedFragment = AddReservationRoomBedBottomSheetFragment()
    var bottomSheetChangeRoomTypeFragment = AddReservationRoomTypeBottomSheetFragment()
    var cameraFragment = CameraFragment()

    private var returnToCheckInActivity = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(
            this,
            com.example.dechproduct.hotelreservationapp.R.layout.activity_add_reservation
        )

        if (callingActivity?.className == CheckInActivity::class.qualifiedName) {
            binding.titleTextView.text = "Add By Walk-In"
            returnToCheckInActivity = true
        } else if (callingActivity?.className == SearchReservationActivity::class.qualifiedName) {
            binding.titleTextView.text = "Edit Reservation"
            try {
                var selectedItem =
                    intent.getParcelableExtra<Booking>(Constants.INTENT_SELECTED_BOOKING)!!
                selectedItem.bookingID?.let { addReservationViewModel.updateInfo(it) }
            } catch (e: Exception) {
            }
        }

        binding.buttonTest.setOnClickListener {
            showDateRangePicker()
        }
        var today = Date()
        binding.tvDateStart.text = convertLongToDate(today.time)
        binding.tvDateEnd.text = convertLongToDate(today.time + (1000 * 60 * 60 * 24))

        binding.btnSubmit.setOnClickListener {
            if (binding.firstNameCustomer.getValue().isNotEmpty() and
                binding.lastNameCustomer.getValue().isNotEmpty() and
                binding.phoneNumber.getValue().isNotEmpty() and
                binding.ID.getValue().isNotEmpty() and
                binding.about.getValue().isNotEmpty() and
                !(
                        binding.edtGuestNumber.text.isNullOrEmpty() and
                                binding.edtChildNumber.text.isNullOrEmpty())
            ) {
                binding.btnSubmit.isEnabled = false

                addReservationViewModel.reservation.guest?.firstName =
                    binding.firstNameCustomer.getValue()
                addReservationViewModel.reservation.guest?.lastName =
                    binding.lastNameCustomer.getValue()
                addReservationViewModel.reservation.guest?.phoneNumber =
                    binding.phoneNumber.getValue()
                addReservationViewModel.reservation.payment?.type =
                    PaymentType.getByDisplayName(binding.paymentType.getValue()) ?: PaymentType.None
                addReservationViewModel.reservation.guest?.verificationID?.id =
                    binding.ID.getValue()
                addReservationViewModel.reservation.guest?.verificationID?.identifyID()
                addReservationViewModel.reservation.guest?.address =
                    binding.about.getValue().split("\n")

                addReservationViewModel.reservation.adultCount =
                    binding.edtGuestNumber.text.toString().toIntOrNull() ?: 0
                addReservationViewModel.reservation.childCount =
                    binding.edtChildNumber.text.toString().toIntOrNull() ?: 0


                addReservationViewModel.reservation.breakfast =
                    binding.checkBoxBreakfast.isChecked
                addReservationViewModel.reservation.room?.smoking =
                    binding.checkBoxSmoking.isChecked

                var dateFormat = SimpleDateFormat("dd-MM-yyyy")
                addReservationViewModel.toOccupy.arrivalDate =
                    dateFormat.parse(binding.tvDateStart.text.toString())
                addReservationViewModel.toOccupy.departDate =
                    dateFormat.parse(binding.tvDateEnd.text.toString())
                try {
                    var room = addReservationViewModel.checkRoomAvailable()

                } catch (e: Exception) {
                }

                addReservationViewModel.reservation.guest?.verificationPhoto =
                    addReservationViewModel.photo

            } else {
                Toast.makeText(applicationContext, "Insufficient Information.", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        binding.buttonCamera.setOnClickListener {
            Toast.makeText(applicationContext, "Camera Button is Tapped.", Toast.LENGTH_LONG).show()
//            val intent =
//                Intent(this@AddReservationActivity, CameraFragment::class.java)
//            startActivity(intent)
            cameraFragment.show(supportFragmentManager, "TAG")
        }

        binding.btnBackMenu.setOnClickListener {
            finish()
        }


        binding.btnPlus.setOnClickListener {
            lifecycleScope.launch {
                addReservationViewModel.increment()
                binding.edtGuestNumber.setText(addReservationViewModel.amount.value.toString())

            }

        }

        binding.btnMinus.setOnClickListener {
            lifecycleScope.launch {
                addReservationViewModel.decrement()
                binding.edtGuestNumber.setText(addReservationViewModel.amount.value.toString())
            }

        }

        binding.btnPlusChild.setOnClickListener {
            lifecycleScope.launch {
                addReservationViewModel.incrementChild()
                binding.edtChildNumber.setText(addReservationViewModel.amountChild.value.toString())
            }
        }

        binding.btnMinusChild.setOnClickListener {
            lifecycleScope.launch {
                addReservationViewModel.decrementChild()
                binding.edtChildNumber.setText(addReservationViewModel.amountChild.value.toString())
            }
        }

        binding.edtGuestNumber.setOnClickListener {
            Log.i("AddReservationActivity", "edittextGuest clicked")
            addReservationViewModel.reservation.adultCount =
                binding.edtGuestNumber.text.toString().toIntOrNull() ?: 0

        }


        binding.edtChildNumber.setOnClickListener {
            Log.i("AddReservationActivity", "edittextChild clicked")
            addReservationViewModel.reservation.adultCount =
                binding.edtChildNumber.text.toString().toIntOrNull() ?: 0

        }

        binding.btnRoomType.setOnClickListener {
            bottomSheetChangeRoomTypeFragment.show(supportFragmentManager, "TAG")
//            if(bottomSheetChangeRoomTypeFragment.isAdded){
//                binding.tvDisplayRoomType.setText(addReservationViewModel.reservation?.room?.beds)
//                Log.e()
//            }
        }

//        binding.tvDisplayRoomType.setOnClickListener{
//            lifecycleScope.launch {
//                binding.tvDisplayRoomType.setText(bottomSheetChangeRoomTypeFragment.toString())
//            }
//        }

        binding.btnRoomBed.setOnClickListener {
            bottomSheetRoomBedFragment.show(supportFragmentManager, "TAG")

        }

        binding.checkBoxBreakfast.setOnClickListener {
            if (binding.checkBoxBreakfast.isChecked) {
                lifecycleScope.launch {
                    addReservationViewModel.breakfastChecked()
                    binding.checkBoxBreakfast.isChecked =
                        addReservationViewModel.checkedBreakfast.value!!

                }


            } else {
                lifecycleScope.launch {
                    addReservationViewModel.breakfastNotChecked()
                    binding.checkBoxBreakfast.isChecked =
                        addReservationViewModel.checkedBreakfast.value!!

                }

            }
            // NOTE -> use "isChecked" for set the checked state of ui -> binding.checkBoxBreakfast.isChecked = true -> The checkbox will checked
        }
        binding.checkBoxSmoking.setOnClickListener {
            if (binding.checkBoxSmoking.isChecked) {
                lifecycleScope.launch {
                    addReservationViewModel.smokingChecked()
                    binding.checkBoxSmoking.isChecked =
                        addReservationViewModel.checkedSmoking.value!!
                }
            } else {
                lifecycleScope.launch {
                    addReservationViewModel.smokingNotChecked()
                    binding.checkBoxSmoking.isChecked =
                        addReservationViewModel.checkedSmoking.value!!

                }
            }
        }
        observeSearchRoom()
        observeAddReservation()

        observeRoomTypeFragment()
        observeBedTypeFragment()

        observeUpdateInfo()
    }

    private fun observeRoomTypeFragment() {
        addReservationViewModel.roomType.observe(this, {
            binding.tvDisplayRoomType.text = it.toString()
        })
    }

    private fun observeBedTypeFragment() {
        addReservationViewModel.bedType.observe(this, {
            binding.tvRoomBed.text = it.toString()
        })
    }

    private fun showDateRangePicker() {
        val dateRangePicker = MaterialDatePicker.Builder
            .dateRangePicker()
            .setTitleText("Select Booking Date ")
            .build()
        //TODO: Set minimum date to current date
        dateRangePicker.show(
            supportFragmentManager,
            "date_range_picker"
        )

        dateRangePicker.addOnPositiveButtonClickListener { datePicked ->
            addReservationViewModel.startDateEpoch = datePicked.first
            addReservationViewModel.endDateEpoch = datePicked.second
            if (addReservationViewModel.startDateEpoch != null && addReservationViewModel.endDateEpoch != null) {
                binding.tvDateStart.text = convertLongToDate(addReservationViewModel.startDateEpoch)
                binding.tvDateEnd.text = convertLongToDate(addReservationViewModel.endDateEpoch)

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

    private fun observeUpdateInfo() {
        addReservationViewModel.loadedReservation.observe(this, {
            when (it) {
                is Resource.Success -> {
                    it.data?.let { reservation ->
                        addReservationViewModel.reservation = reservation

                        binding.firstNameCustomer.setValue(reservation.guest?.firstName!!)
                        binding.lastNameCustomer.setValue(reservation.guest?.lastName!!)
                        for (line in reservation.guest?.address!!) {
                            binding.about.setValue(binding.about.getValue() + line + "\n")
                        }

                        binding.phoneNumber.setValue(reservation.guest?.phoneNumber!!)

                        for (index in 0..(binding.paymentType.getSpinner().count - 1)) {
                            if (binding.paymentType.getSpinner().getItemAtPosition(index)
                                    .toString() == reservation.payment?.type?.toString()
                            ) {
                                binding.paymentType.getSpinner().setSelection(index)
                                break
                            }
                        }

                        binding.ID.setValue(reservation.guest?.verificationID?.id!!)
                        binding.tvDateStart.text = SimpleDateFormat(
                            "dd-MM-yyyy",
                            Locale.getDefault()
                        ).format(reservation.arrivalDate)
                        binding.tvDateEnd.text = SimpleDateFormat(
                            "dd-MM-yyyy",
                            Locale.getDefault()
                        ).format(reservation.departDate)

                        binding.tvDisplayRoomType.text = reservation.room?.type.toString()
                        binding.tvRoomBed.text = reservation.room?.beds.toString()

                        binding.edtGuestNumber.setText(reservation.adultCount?.toString())
                        binding.edtChildNumber.setText(reservation.childCount?.toString())

                        binding.checkBoxBreakfast.isChecked = reservation.breakfast == true
                        binding.checkBoxSmoking.isChecked = reservation.room?.smoking == true
                    }
                }
                is Resource.Failure -> {
                    Toast.makeText(applicationContext, it.throwable.message, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
    }

    private fun observeAddReservation() {
        addReservationViewModel.reserver.observe(this, {
            when (it) {
                is Resource.Success -> {
                    it.data?.let { _ ->
                        Toast.makeText(
                            applicationContext, "Booking Success.",
                            Toast.LENGTH_SHORT
                        ).show()
                        finish()
                    }
                }

                is Resource.Failure -> {
                    Toast.makeText(applicationContext, it.throwable.message, Toast.LENGTH_SHORT)
                        .show()
                    binding.btnSubmit.isEnabled = true
                }

            }
        })
    }

    private fun observeSearchRoom() {
        addReservationViewModel.roomer.observe(this, {
            when (it) {
                is Resource.Success -> {
                    it.data?.let { rooms ->

                        if (rooms.isEmpty()) {
                            Toast.makeText(
                                applicationContext,
                                "No room available, try adjusting the criteria.",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            addReservationViewModel.reservation.room = rooms.first()
                            if (rooms.first().maxCap == addReservationViewModel.reservation.adultCount) {

                                val builder = AlertDialog.Builder(this)
                                builder.setCancelable(false)
                                builder.setMessage("Selected room configuration required additional bed, proceed?")
                                builder.setPositiveButton("Yes",
                                    DialogInterface.OnClickListener { _, _ ->
                                        addReservationViewModel.addReservation()
                                        finish()
                                    })
                                builder.setNegativeButton("No",
                                    DialogInterface.OnClickListener { dialog, _ ->
                                        dialog.cancel()
                                    })
                                builder.create().show()

                            } else {
                                addReservationViewModel.addReservation()
                            }
                        }
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