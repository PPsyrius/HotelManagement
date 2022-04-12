package com.example.dechproduct.hotelreservationapp.presentation.checkin

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dechproduct.hotelreservationapp.R
import com.example.dechproduct.hotelreservationapp.databinding.ActivityCheckInBinding
import com.example.dechproduct.hotelreservationapp.presentation.checkin.adapter.CheckInAdapter
import com.example.dechproduct.hotelreservationapp.presentation.checkinDetail.CheckinDetailActivity
import com.example.dechproduct.hotelreservationapp.presentation.menu.MenuActivity
import com.example.dechproduct.hotelreservationapp.util.swipe.Helper.MySwipeHelper
import com.example.dechproduct.hotelreservationapp.util.swipe.listener.MyButton
import com.example.dechproduct.hotelreservationapp.util.swipe.listener.MyButtonClickListener
import com.example.dechproduct.hotelreservationapp.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CheckInActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheckInBinding
    private val checkInViewModel: CheckInViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBackMenu.setOnClickListener{
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }

        var searchBar = binding.searchBar
        searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if(query == "")
                    lifecycleScope.launch{
                        checkInViewModel.populateReserve()
                    }
                else
                    lifecycleScope.launch {
                        checkInViewModel.searchReserve(query.capitalize())
                    }
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if(newText == "")
                    lifecycleScope.launch{
                        checkInViewModel.populateReserve()
                    }
                else
                    lifecycleScope.launch{
                        checkInViewModel.searchReserve(newText.capitalize())
                    }
                return false
            }
        })

        binding.reservationList.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch{
            checkInViewModel.populateReserve()
        }

//        binding.fabAdd.setOnClickListener{
//            Toast.makeText(applicationContext, "Add Reservation", Toast.LENGTH_SHORT).show()
//            val intent = Intent(this, AddReservationActivity::class.java )
//            startActivity(intent)


        onSwipeHandle()
        observeSearch()

        }

    private fun onSwipeHandle() {
        val swipe = object : MySwipeHelper(this, binding.reservationList, 200) {
            override fun instantiateMyButton(
                viewHolder: RecyclerView.ViewHolder,
                buffer: MutableList<MyButton>
            ) {
                buffer.add(
                    MyButton(this@CheckInActivity,
                        "Check-in",
                        45,
                        R.drawable.ic_baseline_beenhere_24,
                        Color.parseColor("#028A0F"),
                        object : MyButtonClickListener {
                            override fun onClick(pos: Int) {
                                Toast.makeText(
                                    this@CheckInActivity,
                                    "Check-in" + pos,
                                    Toast.LENGTH_SHORT
                                ).show()
                                startSpecificActivity(CheckinDetailActivity::class.java)

                            }
                        }
                    ))

            }

        }
    }

    private fun observeSearch() {
        checkInViewModel.reserver.observe(this, {
            when (it) {
                is Resource.Success -> {
                    it.data?.let { reservationList ->
                        Log.d("CheckInResActivity",reservationList.toString())
                        binding.reservationList.adapter = CheckInAdapter(reservationList)            //here adapter set up recycler view
                    }
                }

                is Resource.Failure -> {
                    Toast.makeText(applicationContext, it.throwable.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun startSpecificActivity(otherActivityClass: Class<*>?) {
        val intent = Intent(applicationContext, otherActivityClass)
        startActivity(intent)
    }


    }


