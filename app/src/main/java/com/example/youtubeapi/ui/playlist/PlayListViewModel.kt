package com.example.youtubeapi.ui.playlist

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.os.Build
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.youtubeapi.BuildConfig.API_KEY
import com.example.youtubeapi.Constants
import com.example.youtubeapi.Constants.MAX_RESULT
import com.example.youtubeapi.model.PlayList
import com.example.youtubeapi.network.RetrofitClient
import com.example.youtubeapi.network.YoutubeApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlayListViewModel : ViewModel() {

    private var youtubeApi: YoutubeApi = RetrofitClient.create()

    fun fetchAllPlayLists(): LiveData<PlayList?> {
        return fetchYoutubeApiPlayList()
    }

    private fun fetchYoutubeApiPlayList(): LiveData<PlayList?> {

        val data = MutableLiveData<PlayList>()

        youtubeApi.fetchAllPlayLists(Constants.PART, Constants.CHANNEL_ID, API_KEY,MAX_RESULT)
            .enqueue(object : Callback<PlayList> {
                override fun onResponse(call: Call<PlayList>, response: Response<PlayList>) {

                    Log.d("tag", "success: " + response.body())

                    if (response.isSuccessful) data.value = response.body()
                    else {

                        Log.d("tag", "error: " + response.code().toString())

                    }

                }

                override fun onFailure(call: Call<PlayList>, t: Throwable) {
                    Log.d("tag", "failure ${t.localizedMessage}")

                }

            })
        return data
    }
}