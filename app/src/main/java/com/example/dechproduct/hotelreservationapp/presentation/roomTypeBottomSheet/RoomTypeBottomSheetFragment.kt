package com.example.dechproduct.hotelreservationapp.presentation.roomTypeBottomSheet

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.dechproduct.hotelreservationapp.R
import com.example.dechproduct.hotelreservationapp.databinding.FragmentRoomTypeBottomSheetBinding
import com.example.dechproduct.hotelreservationapp.presentation.checkinDetail.CheckinDetailActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class RoomTypeBottomSheetFragment (): BottomSheetDialogFragment(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_room_type_bottom_sheet, container, false)
        var btnConfirm = view.findViewById<Button>(R.id.btnConfirmChangeRoom)
        var editRoomType = view.findViewById<EditText>(R.id.edChangeRoomType)

        btnConfirm.setOnClickListener {
            if (editRoomType.text.trim().toString().isNotEmpty()){
                var roomType = editRoomType.text.trim().toString()
                //Handle Here
                Toast.makeText(requireContext(), "Change room type", Toast.LENGTH_LONG).show()
                dismiss()


            }
            else {
                Toast.makeText(requireContext(), "Input required", Toast.LENGTH_LONG).show()
            }
        }


        return view
    }
}