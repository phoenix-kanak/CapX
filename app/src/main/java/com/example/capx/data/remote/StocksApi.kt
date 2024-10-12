package com.example.capx.data.remote

import com.example.capx.data.models.Stock
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface StocksApi {

    @GET("api/v3/stock/list")
    fun getStocks(@Query("apikey") apiKey: String): Call<List<Stock>>
}