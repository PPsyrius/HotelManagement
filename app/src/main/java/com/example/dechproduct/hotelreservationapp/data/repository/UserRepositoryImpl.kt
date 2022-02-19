package com.example.dechproduct.hotelreservationapp.data.repository

import android.content.SharedPreferences
import android.util.Log
import com.example.dechproduct.hotelreservationapp.data.api.UserAPIService
import com.example.dechproduct.hotelreservationapp.data.model.Staff
import com.example.dechproduct.hotelreservationapp.domain.repository.UserRepository
import com.example.dechproduct.hotelreservationapp.util.Constants
import com.example.dechproduct.hotelreservationapp.util.Resource
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userAPI: UserAPIService,
    val sharedPreferences: SharedPreferences): UserRepository{

    override suspend fun login(username: String, password: String):Resource<Staff> {
        return try {
            //val userNode = firebaseDatabase.reference.child(Constants.USER_DB_NODE)
            var staff: Staff = userAPI.getByUserName(username)[0].toStaff()
            var isFound: Boolean = false

            //TODO: Implements password hashing for comparison
            if(staff.password == password){
                isFound = true
            }

            /*
            userNode.orderByChild(Constants.USER_KEY_USERNAME).equalTo(username).get()
                .await().children.map { item ->
                    if (item.child(Constants.USER_KEY_PASSWORD).getValue(String::class.java) == password)
                    {
                        staff.userID =
                            item.child(Constants.USER_KEY_ID).getValue(String::class.java)
                        staff.userName =
                            item.child(Constants.USER_KEY_USERNAME).getValue(String::class.java)
                        staff.displayName =
                            item.child(Constants.USER_KEY_NAME).getValue(String::class.java)
                        isFound =true
                    }
                    else {
                        throw Exception("Authentication Failed.")
                    }
                }
             */

            if(isFound)
                Resource.Success(staff)
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
