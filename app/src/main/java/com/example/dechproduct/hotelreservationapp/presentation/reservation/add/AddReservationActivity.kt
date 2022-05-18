package com.example.dechproduct.hotelreservationapp.presentation.reservation.add

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.CheckBox
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.dechproduct.hotelreservationapp.R
import com.example.dechproduct.hotelreservationapp.databinding.ActivityAddReservationBinding
import com.example.dechproduct.hotelreservationapp.presentation.addReservationRoomBedBottomSheet.AddReservationRoomBedBottomSheetFragment
import com.example.dechproduct.hotelreservationapp.presentation.addReservationRoomTypeBottomSheet.AddReservationRoomTypeBottomSheetFragment
import com.example.dechproduct.hotelreservationapp.presentation.reservation.ReservationMenuActivity
import com.example.dechproduct.hotelreservationapp.presentation.reservation.add.camera.CameraActivity
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
class AddReservationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddReservationBinding
    private val addReservationViewModel: AddReservationViewModel by viewModels()
    //TODO: Title of this activity should adapt to which activity calls it -- Optional?

    var bottomSheetRoomBedFragment = AddReservationRoomBedBottomSheetFragment()
    var bottomSheetChangeRoomTypeFragment = AddReservationRoomTypeBottomSheetFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_reservation)

        binding.buttonTest.setOnClickListener {
            showDateRangePicker()
        }

        binding.btnSubmit.setOnClickListener {

            //TODO: Check no fields are blank --TUNG --* EVERY CASE *
            var fname = findViewById<FormInputText>(R.id.first_name_customer).getValue()
            var lname = findViewById<FormInputText>(R.id.last_name_customer).getValue()
            var phone = findViewById<FormInputText>(R.id.phoneNumber).getValue()
            //TODO: Pass Payment Type object from here -- TUNG
            var payment = findViewById<FormInputSpinner>(R.id.payment_type).getValue()
            var id = findViewById<FormInputText>(R.id.ID).getValue()
            var address = listOf(findViewById<FormInputMultiline>(R.id.about).getValue())



            //TODO: adjust code to binding.item ?? Mekh describe more to TUNG
            //TODO: Divide address into List<String> by new line before passing as 'address' var -- TUNG -- finish?
            var listToAddress: List<String> = emptyList()
            for(addr in address){
                listToAddress.toMutableList().add(addr)
            }
            address = listToAddress


            //TODO: Bind adult/child count to view component --finish? Please Checking  Mekh --
            // TODO !!ATTENTION!!: Handle NumberFomatExceeption when guest or child didnt selected       -- MEKH pls checked (already try)
            var adult_count = binding.edtGuestNumber.text.toString()
            var child_count = binding.edtChildNumber.text.toString()

            //TODO: Check if adult+child count doesn't exceed room max cap -- ?? Mekh describe more to TUNG
            val adultIntFromET: Int? = adult_count.toIntOrNull()
            val childIntFromET: Int? = child_count.toIntOrNull()

            var cbBreakfast =  binding.checkBoxBreakfast.isChecked
            //TODO: Handle Checkbox smoking again (like cbBreakfast) -- Mekh?




            if(fname.isNotEmpty() and lname.isNotEmpty() and phone.isNotEmpty() and payment.isNotEmpty() and id.isNotEmpty() and address.isNotEmpty()){
                lifecycleScope.launch {

                        addReservationViewModel.addReserve(
                            fname, lname, phone,
                            payment, id, address, adultIntFromET, childIntFromET,
                            breakfast = cbBreakfast, isAddonBed = false
                        )



                    Log.i("Addreser", adultIntFromET.toString())
                    Log.i("Addreser2", childIntFromET.toString())
                    Log.i("AddreservationCBTicked5", cbBreakfast.toString())


                    val intent = Intent(this@AddReservationActivity, ReservationMenuActivity::class.java)
                    startActivity(intent)
                }
            }else{
                Toast.makeText(applicationContext,"Insufficient Information.", Toast.LENGTH_SHORT).show()
            }



//            finish()

        }

        binding.buttonCamera.setOnClickListener {
            Toast.makeText(applicationContext, "Camera Button is Tapped.", Toast.LENGTH_LONG).show()
            val intent =
                Intent(this@AddReservationActivity, CameraActivity::class.java)
            startActivity(intent)
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
            // TODO :  ADD NUMBER OF GUEST TO VIEWMODEL WHEN EDITTEXT IS EDITED (optional )

        }


        binding.edtChildNumber.setOnClickListener {
            Log.i("AddReservationActivity", "edittextChild clicked")
            // TODO :  ADD NUMBER OF CHILD TO VIEWMODEL WHEN EDITTEXT IS EDITED (optional )

        }

        binding.btnRoomType.setOnClickListener{
            bottomSheetChangeRoomTypeFragment.show(supportFragmentManager, "TAG")

        }

        binding.btnRoomBed.setOnClickListener {
            bottomSheetRoomBedFragment.show(supportFragmentManager, "TAG")

        }

        binding.checkBoxBreakfast.setOnClickListener {
            if (binding.checkBoxBreakfast.isChecked){
                Toast.makeText(applicationContext, "Checkbox breakfast clicked", Toast.LENGTH_LONG).show()
                lifecycleScope.launch {
                    addReservationViewModel.breakfastChecked()
                    binding.checkBoxBreakfast.isChecked = addReservationViewModel.checkedBreakfast.value!!

                }


            } else {
                Toast.makeText(applicationContext, "Checkbox breakfast not checked", Toast.LENGTH_LONG).show()
                lifecycleScope.launch {
                    addReservationViewModel. breakfastNotChecked()
                    binding.checkBoxBreakfast.isChecked = addReservationViewModel.checkedBreakfast.value!!

                }

            }


            // NOTE -> use "isChecked" for set the checked state of ui -> binding.checkBoxBreakfast.isChecked = true -> The checkbox will checked
        }
        binding.checkBoxSmoking.setOnClickListener {
            Toast.makeText(applicationContext, "Checkbox smoking clicked", Toast.LENGTH_LONG).show()

        // TODO Added here (like cbBreakfast)
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

    private fun observeSubmit() {
        addReservationViewModel.reserver.observe(this, {
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