package com.example.dechproduct.hotelreservationapp.presentation.reservation

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
import com.example.dechproduct.hotelreservationapp.databinding.ActivityReservationMenuBinding
import com.example.dechproduct.hotelreservationapp.presentation.menu.MenuActivity
import com.example.dechproduct.hotelreservationapp.presentation.reservation.add.AddReservationActivity
import com.example.dechproduct.hotelreservationapp.presentation.reservation.search.SearchReservationActivity
import com.example.dechproduct.hotelreservationapp.util.Constants
import javax.inject.Inject

class ReservationMenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReservationMenuBinding

    private lateinit var userInfo: Employee
    @Inject
    lateinit var sharedPreferences:SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_reservation_menu
        )

        binding.btnBackMenu.setOnClickListener{
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
        binding.cvSearch.setOnClickListener{
            val intent = Intent(this, SearchReservationActivity::class.java)
            startActivity(intent)
        }
        binding.cvReservation.setOnClickListener{
            val intent = Intent(this, AddReservationActivity::class.java)
            startActivity(intent)
        }

        updateLoginName()
    }

    private fun updateLoginName() {
        try {

            sharedPreferences = applicationContext.getSharedPreferences(Constants.SHARED_PREF, Context.MODE_PRIVATE)
            binding.textView5.text = sharedPreferences.getString(Constants.SHARED_PREF_SESSION, null).toString()

//            userInfo = intent.getParcelableExtra<Employee>(Constants.INTENT_EXP_USER)!!
//            binding.textView5.text = userInfo.firstName + " " + userInfo.lastName

        } catch (e: Exception) {
            Log.d("ERR:",e.toString())
        }
    }
}