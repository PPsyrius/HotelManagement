package com.example.dechproduct.hotelreservationapp.data.repository

import android.content.SharedPreferences
import android.util.Log
import com.example.dechproduct.hotelreservationapp.data.api.UserAPIService
import com.example.dechproduct.hotelreservationapp.data.model.employee.Access
import com.example.dechproduct.hotelreservationapp.domain.repository.UserRepository
import com.example.dechproduct.hotelreservationapp.util.Resource
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userAPI: UserAPIService,
    val sharedPreferences: SharedPreferences): UserRepository{

    override suspend fun login(username: String, password: String):Resource<Access> {
        return try {

            var access: Access = userAPI.getByUserName(username)[0].toStaff()
            var isFound: Boolean = false

            //TODO: Camera
            //TODO: Implements LogOut system
            //TODO: Implements password hashing for comparison
            if(access.password == password){
                isFound = true
            }

            if(isFound)
                Resource.Success(access)
            else
                throw Exception("Authentication Failed.")
        }

        catch(exception: IndexOutOfBoundsException){
            Resource.Failure(Exception("No Staff Found."))
        }

        catch(exception: Exception) {
            Log.d("UserRepositoryImpl", exception.toString())
            Resource.Failure(exception)
        }

    }

}
