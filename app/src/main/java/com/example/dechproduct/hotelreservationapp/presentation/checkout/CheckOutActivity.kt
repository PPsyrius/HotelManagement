package com.example.dechproduct.hotelreservationapp.presentation.checkout

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
import com.example.dechproduct.hotelreservationapp.databinding.ActivityCheckOutBinding
import com.example.dechproduct.hotelreservationapp.presentation.checkout.adapter.CheckOutAdapter
import com.example.dechproduct.hotelreservationapp.presentation.menu.MenuActivity
import com.example.dechproduct.hotelreservationapp.util.swipe.Helper.MySwipeHelper
import com.example.dechproduct.hotelreservationapp.util.swipe.listener.MyButton
import com.example.dechproduct.hotelreservationapp.util.swipe.listener.MyButtonClickListener
import com.example.dechproduct.hotelreservationapp.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class CheckOutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheckOutBinding
    private val checkOutViewModel: CheckOutViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckOutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBackMenu.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }

        var searchBar = binding.searchBar
        searchBar.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (query == "")

                    checkOutViewModel.populateReserve()
                else

                    checkOutViewModel.searchReserve(query.capitalize())

                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText == "")

                    checkOutViewModel.populateReserve()
                else

                    checkOutViewModel.searchReserve(newText.capitalize())

                return false
            }
        })

        binding.reservationList.layoutManager = LinearLayoutManager(this)
        
        checkOutViewModel.populateReserve()

        onSwipeHandle()
        observeSearch()
        observeCheckOutResolve()

    }

    private fun onSwipeHandle() {
        val swipe = object : MySwipeHelper(this, binding.reservationList, 200) {
            override fun instantiateMyButton(
                viewHolder: RecyclerView.ViewHolder,
                buffer: MutableList<MyButton>
            ) {
                buffer.add(
                    MyButton(this@CheckOutActivity,
                        "Check-Out",
                        45,
                        R.drawable.ic_baseline_beenhere_24,
                        Color.parseColor("#FF5003"),
                        object : MyButtonClickListener {
                            override fun onClick(pos: Int) {
                                Toast.makeText(
                                    this@CheckOutActivity,
                                    "Check-out" + pos,
                                    Toast.LENGTH_SHORT
                                ).show()

                                checkOutViewModel.checkOutReserved(checkOutViewModel.result[pos])

                            }
                        }
                    ))

            }

        }
    }

    private fun observeSearch() {
        checkOutViewModel.reserver.observe(this, {
            when (it) {
                is Resource.Success -> {
                    it.data?.let { reservationList ->
                        Log.d("CheckOutResActivity", reservationList.toString())
                        checkOutViewModel.result = reservationList
                        binding.reservationList.adapter =
                            CheckOutAdapter(checkOutViewModel.result)            //here adapter set up recycler view
                    }
                }

                is Resource.Failure -> {
                    Toast.makeText(applicationContext, it.throwable.message, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
    }

    private fun observeCheckOutResolve() {
        checkOutViewModel.resolveReservation.observe(this, {
            when (it) {
                is Resource.Success -> {
                    it.data?.let { reservation ->
                        Log.d("CheckOutResActivity", reservation.toString())
                        Toast.makeText(
                            applicationContext,
                            "Check-Out Successful!",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        val intent =
                            Intent(this@CheckOutActivity, MenuActivity::class.java)
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