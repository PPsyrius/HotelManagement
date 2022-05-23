package com.example.dechproduct.hotelreservationapp.data.repository

import android.content.SharedPreferences
import android.util.Base64
import android.util.Log
import com.example.dechproduct.hotelreservationapp.data.api.UserAPIService
import com.example.dechproduct.hotelreservationapp.data.model.employee.Access
import com.example.dechproduct.hotelreservationapp.domain.repository.UserRepository
import com.example.dechproduct.hotelreservationapp.util.Resource
import java.security.spec.KeySpec
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userAPI: UserAPIService,
    val sharedPreferences: SharedPreferences): UserRepository{

    private lateinit var keySpecs :KeySpec
    private var factory: SecretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1")

    override suspend fun login(username: String, password: String):Resource<Access> {
        return try {
            keySpecs = PBEKeySpec(password.toCharArray(),username.toByteArray(),65536,128)
            val pByte = Base64.encodeToString(factory.generateSecret(keySpecs).encoded,Base64.NO_WRAP)

            //TODO: Remove this line for production build
            Log.d("PASSWORD:", pByte)

            var access: Access = userAPI.getByUserName(username)[0].toAccess()
            var isFound: Boolean = false

            if(access.password == pByte){
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
