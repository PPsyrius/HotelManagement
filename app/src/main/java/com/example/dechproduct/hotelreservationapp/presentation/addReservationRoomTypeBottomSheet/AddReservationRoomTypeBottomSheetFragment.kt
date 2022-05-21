package com.example.dechproduct.hotelreservationapp.presentation.addReservationRoomTypeBottomSheet

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dechproduct.hotelreservationapp.R
import com.example.dechproduct.hotelreservationapp.data.model.room.Room
import com.example.dechproduct.hotelreservationapp.data.model.room.RoomType
import com.example.dechproduct.hotelreservationapp.databinding.FragmentAddReservationRoomTypeBottomSheetBinding
import com.example.dechproduct.hotelreservationapp.databinding.FragmentRoomTypeBottomSheetBinding
import com.example.dechproduct.hotelreservationapp.presentation.reservation.add.AddReservationViewModel
import com.example.dechproduct.hotelreservationapp.presentation.roomTypeBottomSheet.RoomTypeAdapter
import com.example.dechproduct.hotelreservationapp.presentation.roomTypeBottomSheet.RoomTypeBottomSheetViewModel
import com.example.dechproduct.hotelreservationapp.util.Resource
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddReservationRoomTypeBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var roomTypeBinding: FragmentAddReservationRoomTypeBottomSheetBinding
    private val roomTypeViewModel: AddReservationRoomTypeBottomSheetViewModel by viewModels()
    private val addReservationViewModel: AddReservationViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(
            R.layout.fragment_add_reservation_room_type_bottom_sheet,
            container,
            false
        )
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        roomTypeBinding = FragmentAddReservationRoomTypeBottomSheetBinding.bind(view)

//        var searchBar = roomTypeBinding.searchBar
//        searchBar.setOnQueryTextListener(object :
//            androidx.appcompat.widget.SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String): Boolean {
//                if (query == "")
//                    lifecycleScope.launch {
//                        roomTypeViewModel.populateReserve()
//                    }
//                else
//                    lifecycleScope.launch {
//                        roomTypeViewModel.searchReserve(query.capitalize())
//                    }
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String): Boolean {
//                if (newText == "")
//                    lifecycleScope.launch {
//                        roomTypeViewModel.populateReserve()
//                    }
//                else
//                    lifecycleScope.launch {
//                        roomTypeViewModel.searchReserve(newText.capitalize())
//                    }
//                return false
//            }
//        })
//

        roomTypeBinding.rvRoomAvailableList.layoutManager = LinearLayoutManager(context)

        lifecycleScope.launch {
            roomTypeViewModel.populateReserve()
        }

        roomTypeBinding.rvRoomAvailableList.adapter =
            AddReservationRoomTypeBottomSheetAdapter(
                RoomType.values().toMutableList(),
                this@AddReservationRoomTypeBottomSheetFragment::onRecyclerItemClicked
            )
        //observeSearch()
    }

    private fun onRecyclerItemClicked(roomType: RoomType) {
        Toast.makeText(context, roomType.toString(), Toast.LENGTH_SHORT).show()

        addReservationViewModel.reservation?.room?.type = roomType

        dismiss()
    }

    private fun observeSearch() {
        roomTypeViewModel.reserver.observe(this, {
            when (it) {
                is Resource.Success -> {
                    it.data?.let { reservationList ->
                        Log.d("AddReservation", reservationList.toString())
//                        roomTypeBinding.rvRoomAvailableList.adapter =
//                            AddReservationRoomTypeBottomSheetAdapter(reservationList)            //here adapter set up recycler view
                    }
                }

                is Resource.Failure -> {
                    Toast.makeText(context, it.throwable.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}


