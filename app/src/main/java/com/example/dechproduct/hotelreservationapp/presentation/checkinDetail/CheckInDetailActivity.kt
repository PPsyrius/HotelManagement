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
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class CheckInDetailActivity : AppCompatActivity() {


    //TODO: Handle Layout to Responsive --TUNG

    private lateinit var binding: ActivityCheckinDetailBinding    // <- can click here to open the xml that related
    private val checkInDetailViewModel: CheckinDetailViewModel by viewModels()


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

        // you can use "binding.VIEWNAME" to represent findViewByID
        // ex)      val displayName = findViewById<TextView>(R.id.textView5)
        // with ->  val displayName = binding.textView5     that's all! (no need <Type of view> and findVeiwByID) -> read viewbinding library

        // TODO: Set default text to something formal or empty("Samson Bujova" -> "") --finish--
        binding.btnBackMenu.setOnClickListener {
            finish()
        }

        binding.tvEditRoomType.setOnClickListener {
            bottomSheetChangeRoomTypeFragment.show(supportFragmentManager, "TAG")
        }

//        binding.roomType.text = "Suite"
//
//
//        binding.tvCheckInDate.text = "11-03-2023"
//
//        binding.tvCheckInDate.text = "13-03-2023"


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
            bottomSheetCheckingRoomAvailableFragment.show(supportFragmentManager, "TAG")
        }


        binding.tvSelectRoomBed.setOnClickListener {
            bottomSheetRoomBedFragment.show(supportFragmentManager, "TAG")
        }

        binding.btnConfirmationCheckIn.setOnClickListener {

//            lifecycleScope.launch { checkInDetailViewModel.checkInReserved() }
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
        sharedPreferences = applicationContext.getSharedPreferences(
            Constants.SHARED_PREF_NAME,
            Context.MODE_PRIVATE
        )
//        Log.d("HERH:", sharedPreferences.getString(
//            Constants.RESERVED_ID,
//            null).toString()
//        )

        lifecycleScope.launch {
            checkInDetailViewModel.updateInfo(
                sharedPreferences.getString(
                    Constants.RESERVED_ID,
                    null
                ).toString()
            )
        }
    }

    private fun observeUpdateInfo() {
        checkInDetailViewModel.selected.observe(this, {
            when (it) {
                is Resource.Success -> {
                    it.data?.let { reservation ->
                        Log.d("CheckInResActivity", reservation.toString())
//                        Toast.makeText(
//                            applicationContext,
//                            reservation.toString(),
//                            Toast.LENGTH_SHORT
//                        ).show()
                        checkInDetailViewModel.reservation = reservation
                        //TODO: If property is null, display nothing (e.g. RoomType = null -> UI field should be blank) --finish?--
                        binding.tvGuestName.text =
                            reservation.guest?.firstName + " " + reservation.guest?.lastName
                        binding.roomType.text = reservation.room?.type.toString()
                        binding.tvCheckInDate.text = SimpleDateFormat(
                            "dd-MM-yyyy",
                            Locale.getDefault()
                        ).format(reservation.arrivalDate)
                        binding.tvCheckOutDate.text = SimpleDateFormat(
                            "dd-MM-yyyy",
                            Locale.getDefault()
                        ).format(reservation.departDate)

                        binding.tvDisplayRoomBed.text = reservation.room?.beds.toString()
                        //TODO: Set binding to reservation.adultCount, reservation.childCount       --finish?--

                        binding.edtGuestNumber.setText(reservation.adultCount?.toString())

                        binding.edtChildNumber.setText(reservation.childCount?.toString())


                        if (reservation.breakfast == true) {
                            binding.cbBreakfast.isChecked = true


                        } else {
                            //TODO: Set check box false -> finished?
                            binding.cbSmoking.isChecked = false
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

    private fun observeCheckInResolve() {
        checkInDetailViewModel.resolve.observe(this, {
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
                        val intent =
                            Intent(this@CheckInDetailActivity, MenuActivity::class.java)
                        startActivity(intent)
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
