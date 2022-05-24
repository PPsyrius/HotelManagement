package com.example.dechproduct.hotelreservationapp.presentation.roomAvailableBottomSheet

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dechproduct.hotelreservationapp.R
import com.example.dechproduct.hotelreservationapp.data.model.booking.BookingStatus
import com.example.dechproduct.hotelreservationapp.data.model.room.*
import com.example.dechproduct.hotelreservationapp.databinding.FragmentRoomAvailableBottomSheetBinding
import com.example.dechproduct.hotelreservationapp.presentation.checkinDetail.CheckinDetailViewModel
import com.example.dechproduct.hotelreservationapp.presentation.roomAvailableBottomSheet.adapter.RoomAvailableAdapter
import com.example.dechproduct.hotelreservationapp.util.Resource
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RoomAvailableBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var roomAvailableBinding: FragmentRoomAvailableBottomSheetBinding
    private val roomAvailableviewModel: RoomAvailableBottomSheetViewModel by viewModels()
    private val checkinDetailViewModel: CheckinDetailViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_room_available_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        roomAvailableBinding = FragmentRoomAvailableBottomSheetBinding.bind(view)

        var searchBar = roomAvailableBinding.searchBar
        searchBar.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                try {
                    launchSearch(query)
                } catch (e: Exception) {
                }
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                try {
                    launchSearch(newText)
                } catch (e: Exception) {
                }
                return false
            }
        })

        roomAvailableBinding.rvRoomAvailableList.layoutManager = LinearLayoutManager(context)

        launchSearch()
        observeSearch()
    }

    private fun launchSearch(keyword: String = "") {
        try {
            var availableRoomStatus: MutableList<RoomStatus> = mutableListOf<RoomStatus>()

            if (checkinDetailViewModel.reservation.status == BookingStatus.GUARANTEED) {

                roomAvailableviewModel.getRoom(checkinDetailViewModel.reservation.room?.roomID.toString())

            } else {
                availableRoomStatus.add(RoomStatus.READY)
                var x = checkinDetailViewModel.roomConfig

                lifecycleScope.launch() {
                    roomAvailableviewModel.searchRoom(
                        keyword = keyword,
                        roomType = checkinDetailViewModel.roomConfig.type ?: RoomType.NONE,
                        bedType = checkinDetailViewModel.roomConfig.beds ?: BedType.NONE,
                        smoking = checkinDetailViewModel.roomConfig.smoking ?: false,
                        roomStatus = availableRoomStatus,
                        occupancy = Occupancy(
                            checkinDetailViewModel.reservation.arrivalDate,
                            checkinDetailViewModel.reservation.departDate,
                            OccupancyStatus.NONE
                        ),
                        adultCount = checkinDetailViewModel.reservation.adultCount!!,
                        childCount = checkinDetailViewModel.reservation.childCount!!
                    )
                }
            }
        } catch (e: Exception) {
            Log.d("ERR", e.toString())
        }
    }

    private fun onRecyclerItemClicked(room: Room) {
        //Toast.makeText(context, room.toString(), Toast.LENGTH_SHORT).show()
        checkinDetailViewModel.roomID.postValue(room.roomID)
        checkinDetailViewModel.selectedRoom = room
        checkinDetailViewModel.disableButton.postValue(false)

        dismiss()
    }

    private fun observeSearch() {
        roomAvailableviewModel.roomer.observe(this, {
            when (it) {
                is Resource.Success -> {
                    it.data?.let { roomList ->
                        Log.d("CheckInResActivity1", roomList.toString())
                        if (checkinDetailViewModel.roomConfig.beds == checkinDetailViewModel.reservation.room?.beds &&
                            checkinDetailViewModel.roomConfig.type == checkinDetailViewModel.reservation.room?.type &&
                            checkinDetailViewModel.roomConfig.smoking == checkinDetailViewModel.reservation.room?.smoking
                        ) {
                            checkinDetailViewModel.reservation.room?.let { it1 -> roomList.add(it1) }
                        }
                        roomAvailableBinding.rvRoomAvailableList.adapter =
                            RoomAvailableAdapter(
                                roomList,
                                this@RoomAvailableBottomSheetFragment::onRecyclerItemClicked
                            )            //here adapter set up recycler view
                    }
                }

                is Resource.Failure -> {
                    Toast.makeText(context, it.throwable.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }


}