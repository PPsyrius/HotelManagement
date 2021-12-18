package com.example.dechproduct.hotelreservationapp.presentation.reservation.reservation_search

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.dechproduct.hotelreservationapp.data.model.APIResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.dechproduct.hotelreservationapp.util.NewsResource
import com.example.dechproduct.hotelreservationapp.domain.usecase.unused.GetNewHeadlinesUseCase
import com.example.dechproduct.hotelreservationapp.domain.usecase.unused.GetSearchedNewsUseCase
import java.lang.Exception

class SearchReservationViewModel(

    private val app: Application,
    private val getNewHeadlinesUseCase: GetNewHeadlinesUseCase,
    private val getSearchedNewsUseCase: GetSearchedNewsUseCase
) : AndroidViewModel(app) {

    val newsHeadlines: MutableLiveData<NewsResource<APIResponse>> = MutableLiveData()

    fun getNewsHeadLines(country: String, page: Int) = viewModelScope.launch(Dispatchers.IO) {
        newsHeadlines.postValue(NewsResource.Loading())
        try {
            if (isInternetAvailable(app)) {
                val apiResult = getNewHeadlinesUseCase.execute(country, page)
                newsHeadlines.postValue(apiResult)
            } else {
                newsHeadlines.postValue(NewsResource.Error("Internet is not available"))
            }
        } catch (e: Exception) {
            newsHeadlines.postValue(NewsResource.Error(e.message.toString()))
        }


    }

    private fun isInternetAvailable(context: Context): Boolean {
        if (context == null) return false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false
    }

    //search
    val searchedNews: MutableLiveData<NewsResource<APIResponse>> = MutableLiveData()

    fun searchNews(
        country: String,
        searchedQuery: String,
        page: Int
    ) = viewModelScope.launch {      //use network bg for coroutine
        searchedNews.postValue(NewsResource.Loading())

        try {
            if (isInternetAvailable(app)) { //get search resukt using use case insatnce
                val response = getSearchedNewsUseCase.execute(
                    country,
                    searchedQuery,
                    page
                )
                searchedNews.postValue(response)
            } else {
                searchedNews.postValue(NewsResource.Error("No Interner Connection"))
            }
        } catch (
            e: Exception
        ) {
            searchedNews.postValue(NewsResource.Error(e.message.toString()))
        }
    }
}