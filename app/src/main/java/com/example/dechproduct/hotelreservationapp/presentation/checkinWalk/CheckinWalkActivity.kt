package com.example.dechproduct.hotelreservationapp.presentation.checkinWalk

/*
    override fun onCreate(){
        observeAddReserveResolve()
        observeCheckInResolve()

        //TODO:Button listener to call -> checkInWalkViewModel.addReserve()
    }

    private fun observeAddReserveResolve() {
        checkInDetailViewModel.builtReserve.observe(this, {
            when (it) {
                is Resource.Success -> {
                    it.data?.let { reservation ->
                        checkInWalkViewModel.checkInReserve(reservation)
                    }
                }
                is Resource.Failure -> {
                    Toast.makeText(applicationContext, it.throwable.message, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
    }

    private fun observeCheckInResolve() {
        checkInDetailViewModel.resolve.observe(this, {
            when (it) {
                is Resource.Success -> {
                    it.data?.let { reservation ->
                        Toast.makeText(
                            applicationContext,
                            "Check-In Successful!",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        val intent =
                            Intent(this@CheckinWalkActivity, MenuActivity::class.java)
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
*/
