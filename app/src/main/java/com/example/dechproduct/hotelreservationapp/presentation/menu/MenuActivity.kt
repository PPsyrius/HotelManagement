package com.example.dechproduct.hotelreservationapp.presentation.menu

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.dechproduct.hotelreservationapp.R
import com.example.dechproduct.hotelreservationapp.data.model.employee.Employee
import com.example.dechproduct.hotelreservationapp.databinding.ActivityMenu2Binding
import com.example.dechproduct.hotelreservationapp.presentation.checkin.CheckInActivity
import com.example.dechproduct.hotelreservationapp.presentation.checkout.CheckOutActivity
import com.example.dechproduct.hotelreservationapp.presentation.login.LoginActivity
import com.example.dechproduct.hotelreservationapp.presentation.reservation.ReservationMenuActivity
import com.example.dechproduct.hotelreservationapp.util.Constants
import javax.inject.Inject

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenu2Binding

    private lateinit var userInfo: Employee
    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_menu2
        )

        binding.btnBackMenu.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.cvReservation.setOnClickListener {
            val intent = Intent(this, ReservationMenuActivity::class.java)
            startActivity(intent)
        }

        binding.cvCheckIn.setOnClickListener {
            val intent = Intent(this, CheckInActivity::class.java)
            startActivity(intent)
        }

        binding.cvCheckOut.setOnClickListener {
            val intent = Intent(this, CheckOutActivity::class.java)
            startActivity(intent)
        }

        updateLoginName()
    }

    override fun onBackPressed() {
        moveTaskToBack(true);

    }

    private fun updateLoginName() {
        try {
        sharedPreferences = applicationContext.getSharedPreferences(Constants.SHARED_PREF, Context.MODE_PRIVATE)
        binding.textView5.text = sharedPreferences.getString(Constants.SHARED_PREF_SESSION, null).toString()

//            userInfo = intent.getParcelableExtra<Employee>(Constants.INTENT_EXP_USER)!!
//            binding.textView5.text = userInfo.position + " " + userInfo.firstName + " " + userInfo.lastName

        } catch (e: Exception) {
            Log.d("ERR:",e.toString())
        }
    }

}