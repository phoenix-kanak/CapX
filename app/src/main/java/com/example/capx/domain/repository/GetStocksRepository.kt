package com.example.capx.domain.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.capx.data.models.Stock
import com.example.capx.data.remote.StocksApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetStocksRepository(private val stocksApi: StocksApi) {

    companion object {
        fun create(): StocksApi {
            return NetworkClient().getRetrofit().create(StocksApi::class.java)
        }
    }

    fun getStocks(apiKey: String): LiveData<List<Stock>> {
        val data = MutableLiveData<List<Stock>>()
        stocksApi.getStocks(apiKey).enqueue(object : Callback<List<Stock>> {
            override fun onResponse(p0: Call<List<Stock>>, p1: Response<List<Stock>>) {
                if (p1.isSuccessful) {
                    data.value = p1.body()
                    Log.d("response", "${p1.body()}")
                } else {
                    data.value = null
                }
            }

            override fun onFailure(p0: Call<List<Stock>>, p1: Throwable) {
                data.value = null
                Log.e("API Error", "Failed to fetch stocks", p1)
            }

        })
        return data
    }
}