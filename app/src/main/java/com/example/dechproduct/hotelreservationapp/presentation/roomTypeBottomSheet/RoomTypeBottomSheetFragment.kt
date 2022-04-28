package com.example.dechproduct.hotelreservationapp.presentation.roomTypeBottomSheet

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dechproduct.hotelreservationapp.R
import com.example.dechproduct.hotelreservationapp.databinding.FragmentRoomBedBottomSheetBinding
import com.example.dechproduct.hotelreservationapp.databinding.FragmentRoomTypeBottomSheetBinding
import com.example.dechproduct.hotelreservationapp.databinding.RoomBedItemBinding
import com.example.dechproduct.hotelreservationapp.presentation.checkinDetail.CheckinDetailActivity
import com.example.dechproduct.hotelreservationapp.util.Resource
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint

class RoomTypeBottomSheetFragment (): BottomSheetDialogFragment(){

    private lateinit var roomTypeBinding: FragmentRoomTypeBottomSheetBinding
    private val roomTypeViewModel:  RoomTypeBottomSheetViewModel by viewModels ()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_room_type_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        roomTypeBinding = FragmentRoomTypeBottomSheetBinding.bind(view)

        var searchBar = roomTypeBinding.searchBar
        searchBar.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if(query == "")
                    lifecycleScope.launch{
                        roomTypeViewModel.populateReserve()
                    }
                else
                    lifecycleScope.launch {
                        roomTypeViewModel.searchReserve(query.capitalize())
                    }
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if(newText == "")
                    lifecycleScope.launch{
                        roomTypeViewModel.populateReserve()
                    }
                else
                    lifecycleScope.launch{
                        roomTypeViewModel.searchReserve(newText.capitalize())
                    }
                return false
            }
        })
        roomTypeBinding.rvRoomAvailableList.layoutManager = LinearLayoutManager(context)

        lifecycleScope.launch{
            roomTypeViewModel.populateReserve()
        }
        observeSearch()

    }


    private fun observeSearch() {
        roomTypeViewModel.reserver.observe(this, {
            when (it) {
                is Resource.Success -> {
                    it.data?.let { reservationList ->
                        Log.d("CheckInResActivity1",reservationList.toString())
                        roomTypeBinding.rvRoomAvailableList.adapter = RoomTypeAdapter(reservationList)            //here adapter set up recycler view
                    }
                }

                is Resource.Failure -> {
                    Toast.makeText(context, it.throwable.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}