package com.example.dechproduct.hotelreservationapp.presentation.checkinDetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dechproduct.hotelreservationapp.R
import com.example.dechproduct.hotelreservationapp.databinding.ActivityCheckinDetailBinding

class CheckinDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheckinDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckinDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}