package com.example.dechproduct.hotelreservationapp.presentation.checkinDetail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.dechproduct.hotelreservationapp.databinding.ActivityCheckinDetailBinding
import com.example.dechproduct.hotelreservationapp.presentation.reservation.ReservationMenuActivity
import com.example.dechproduct.hotelreservationapp.presentation.roomAvailableBottomSheet.RoomAvailableBottomSheetFragment
import com.example.dechproduct.hotelreservationapp.presentation.roomBedBottomSheet.adapter.adapter.RoomBedBottomSheetFragment
import com.example.dechproduct.hotelreservationapp.presentation.roomTypeBottomSheet.RoomTypeBottomSheetFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CheckinDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheckinDetailBinding    // <- can click here to open the xml that related
    private  val viewmodel : CheckinDetailViewModel by viewModels()



    var  bottomSheetChangeRoomTypeFragment = RoomTypeBottomSheetFragment()

    var bottomSheetCheckingRoomAvailableFragment = RoomAvailableBottomSheetFragment()

    var bottomSheetRoomBedFragment = RoomBedBottomSheetFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckinDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // you can use "binding.VIEWNAME" to represent findViewByID
        // ex)      val displayName = findViewById<TextView>(R.id.textView5)
        // with ->  val displayName = binding.textView5     that's all! (no need <Type of view> and findVeiwByID) -> read viewbinding library


        binding.btnBackMenu.setOnClickListener{
            val intent = Intent(this, ReservationMenuActivity::class.java)
            finish()
        }

        binding.tvEditRoomType.setOnClickListener{
            bottomSheetChangeRoomTypeFragment.show(supportFragmentManager, "TAG")
        }

        binding.roomType.text = "Suite"


        binding.tvCheckInDate.text = "11-03-2023"

        binding.tvCheckInDate.text = "13-03-2023"



        binding.btnPlus.setOnClickListener {
            lifecycleScope.launch {
                viewmodel.increment()
                binding.edtGuestNumber.setText(viewmodel.amount.value.toString())

            }

        }

        binding.btnMinus.setOnClickListener {
            lifecycleScope.launch {
                viewmodel.decrement()
                binding.edtGuestNumber.setText(viewmodel.amount.value.toString())
            }

        }

        binding.btnPlusChild.setOnClickListener {
            lifecycleScope.launch{
                viewmodel.incrementChild()
                binding.edtChildNumber.setText(viewmodel.amountChild.value.toString())
            }
        }

        binding.btnMinusChild.setOnClickListener {
            lifecycleScope.launch{
                viewmodel.decrementChild()
                binding.edtChildNumber.setText(viewmodel.amountChild.value.toString())
            }
        }


        binding.edtGuestNumber.setOnClickListener {
           Log.i("CheckInDetailActivity", "edittextGuest clicked")
        }


        binding.edtChildNumber.setOnClickListener {
            Log.i("CheckInDetailActivity", "edittextChild clicked")
        }

        binding.tvCheckingRoom.setOnClickListener{
            bottomSheetCheckingRoomAvailableFragment.show(supportFragmentManager,"TAG")
        }


        binding.tvSelectRoomBed.setOnClickListener {
            bottomSheetRoomBedFragment.show(supportFragmentManager,"TAG")
        }

        binding.btnConfirmationCheckIn.setOnClickListener{
            Toast.makeText(applicationContext, "Confirmation Check in clicked",Toast.LENGTH_LONG).show()
        }

        binding.cbBreakfast.setOnClickListener {
            Toast.makeText(applicationContext, "checkbox breakfast  clicked",Toast.LENGTH_LONG).show()

        }

        binding.cbSmoking.setOnClickListener {
            Toast.makeText(applicationContext, "checkbox smoking  clicked",Toast.LENGTH_LONG).show()

        }


        }

    }
