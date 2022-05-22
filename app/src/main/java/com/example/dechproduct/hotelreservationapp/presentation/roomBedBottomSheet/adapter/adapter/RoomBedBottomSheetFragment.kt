package com.example.dechproduct.hotelreservationapp.presentation.roomBedBottomSheet.adapter.adapter

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
import com.example.dechproduct.hotelreservationapp.data.model.room.BedType
import com.example.dechproduct.hotelreservationapp.data.model.room.RoomType
import com.example.dechproduct.hotelreservationapp.databinding.FragmentRoomBedBottomSheetBinding
import com.example.dechproduct.hotelreservationapp.presentation.reservation.add.AddReservationViewModel
import com.example.dechproduct.hotelreservationapp.presentation.roomTypeBottomSheet.RoomTypeAdapter
import com.example.dechproduct.hotelreservationapp.util.Resource
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RoomBedBottomSheetFragment : BottomSheetDialogFragment(){
    private lateinit var  roomBedBinding : FragmentRoomBedBottomSheetBinding
    private val roomBedViewModel : RoomBedBottomSheetViewModel by viewModels()
    private val addReservationViewModel: AddReservationViewModel by activityViewModels()


    override fun onCreateView(
       inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_room_bed_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        roomBedBinding = FragmentRoomBedBottomSheetBinding.bind(view)

//        var searchBar = roomBedBinding.searchBar
//        searchBar.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String): Boolean {
//                if(query == "")
//                    lifecycleScope.launch{
//                        roomBedViewModel.populateReserve()
//                    }
//                else
//                    lifecycleScope.launch {
//                        roomBedViewModel.searchReserve(query.capitalize())
//                    }
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String): Boolean {
//                if(newText == "")
//                    lifecycleScope.launch{
//                        roomBedViewModel.populateReserve()
//                    }
//                else
//                    lifecycleScope.launch{
//                        roomBedViewModel.searchReserve(newText.capitalize())
//                    }
//                return false
//            }
//        })

        roomBedBinding.rvRoomAvailableList.layoutManager = LinearLayoutManager(context)

        lifecycleScope.launch{
            roomBedViewModel.populateReserve()
        }

        roomBedBinding.rvRoomAvailableList.adapter =
            RoomBedAdapter(
                BedType.values().toMutableList(),
                this::onRecyclerItemClicked
            )



      //  observeSearch()

    }

    private fun onRecyclerItemClicked(bedType: BedType) {
        Toast.makeText(context, bedType.toString(), Toast.LENGTH_SHORT).show()

        addReservationViewModel.reservation?.room?.beds = bedType

        dismiss()
    }

    private fun observeSearch() {
        roomBedViewModel.reserver.observe(this, {
            when (it) {
                is Resource.Success -> {
                    it.data?.let { reservationList ->
                        Log.d("CheckInResActivity1",reservationList.toString())
                       // roomBedBinding.rvRoomAvailableList.adapter = RoomBedAdapter(reservationList)            //here adapter set up recycler view
                    }
                }

                is Resource.Failure -> {
                    Toast.makeText(context, it.throwable.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

}