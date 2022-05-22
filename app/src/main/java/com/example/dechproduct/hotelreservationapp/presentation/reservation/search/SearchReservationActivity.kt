package com.example.dechproduct.hotelreservationapp.presentation.reservation.search

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dechproduct.hotelreservationapp.databinding.ActivitySearchReservationactivityBinding
import com.example.dechproduct.hotelreservationapp.presentation.reservation.ReservationMenuActivity
import com.example.dechproduct.hotelreservationapp.presentation.reservation.add.AddReservationActivity
import com.example.dechproduct.hotelreservationapp.util.swipe.Helper.MySwipeHelper
import com.example.dechproduct.hotelreservationapp.presentation.reservation.search.adapter.SearchAdapter
import com.example.dechproduct.hotelreservationapp.util.Constants
import com.example.dechproduct.hotelreservationapp.util.swipe.listener.MyButton
import com.example.dechproduct.hotelreservationapp.util.swipe.listener.MyButtonClickListener
import com.example.dechproduct.hotelreservationapp.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchReservationActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchReservationactivityBinding
    private val searchReservationViewModel: SearchReservationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchReservationactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBackMenu.setOnClickListener {
            val intent = Intent(this, ReservationMenuActivity::class.java)
            startActivity(intent)
        }

        var searchBar = binding.searchBar
        searchBar.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return searchReservationViewModel.queryHandle(query)
            }
            override fun onQueryTextChange(newText: String): Boolean {
                return searchReservationViewModel.queryHandle(newText)
            }
        })

        binding.reservationList.layoutManager = LinearLayoutManager(this)

        searchReservationViewModel.queryHandle("")

        binding.fabAdd.setOnClickListener {
            Toast.makeText(applicationContext, "Add Reservation", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, AddReservationActivity::class.java)
            startActivity(intent)
        }

        onSwipeHandle()
        observeSearch()
        observeRemove()
    }

    // Add Swipe
    private fun onSwipeHandle() {
        val swipe = object : MySwipeHelper(this, binding.reservationList, 200) {
            override fun instantiateMyButton(
                viewHolder: RecyclerView.ViewHolder,
                buffer: MutableList<MyButton>
            ) {
                buffer.add(
                    MyButton(this@SearchReservationActivity,
                        "Delete",
                        30,
                        com.example.dechproduct.hotelreservationapp.R.drawable.ic_baseline_delete_24,
                        Color.parseColor("#C0362C"),
                        object : MyButtonClickListener {
                            override fun onClick(pos: Int) {
                                searchReservationViewModel.removeReservation(
                                    searchReservationViewModel.reservation[pos]
                                )
                            }
                        }
                    ))

                buffer.add(
                    MyButton(this@SearchReservationActivity,
                        "Update",
                        30,
                        com.example.dechproduct.hotelreservationapp.R.drawable.ic_edit_24,
                        Color.parseColor("#F8D568"),
                        object : MyButtonClickListener {
                            override fun onClick(pos: Int) {
                                val intent =
                                    Intent(applicationContext, AddReservationActivity::class.java)
                                intent.putExtra(
                                    Constants.INTENT_SELECTED_BOOKING,
                                    searchReservationViewModel.reservation[pos]
                                )
                                startActivityForResult(intent, 0)
                            }
                        }
                    ))
            }

        }
    }

    private fun observeSearch() {
        searchReservationViewModel.getter.observe(this, {
            when (it) {
                is Resource.Success -> {
                    it.data?.let { reservationList ->
                        searchReservationViewModel.reservation = reservationList
                        Log.d("SearchResActivity", reservationList.toString())
                        binding.reservationList.adapter =
                            SearchAdapter(searchReservationViewModel.reservation)            //here adapter set up recycler view
                    }
                }

                is Resource.Failure -> {
                    Toast.makeText(applicationContext, it.throwable.message, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
    }

    private fun observeRemove() {
        searchReservationViewModel.setter.observe(this, {
            when (it) {
                is Resource.Success -> {
                    it.data?.let { reservation ->
                        Toast.makeText(
                            applicationContext,
                            "Removed Booking: " + reservation.bookingID,
                            Toast.LENGTH_SHORT
                        )
                            .show()

                        finish()
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
