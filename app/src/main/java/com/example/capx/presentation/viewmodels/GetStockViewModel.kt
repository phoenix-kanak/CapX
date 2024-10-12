package com.example.capx.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.capx.data.models.Stock
import com.example.capx.domain.repository.GetStocksRepository

class GetStockViewModel:ViewModel() {
    private val repository: GetStocksRepository = GetStocksRepository(GetStocksRepository.create())

    private val _stocks = MutableLiveData<List<Stock>>()
    val stocks: LiveData<List<Stock>> get() = _stocks

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun fetchStocks(apikey:String){
        try{
            repository.getStocks(apikey).observeForever{ response ->
                if (response != null) {
                    Log.d("GetStocksViewModel", "Data fetched successfully: ${response}")
                    _stocks.value = response
                } else {
                    Log.d("GetStocksViewModel", "Failed to fetch data")
                    _stocks.value = null
                    _errorMessage.value = "Failed to fetch data."
                }
            }

        }catch (e:Exception){
            Log.e("GetStocksViewModel", "Exception occurred: ${e.message}")
            _errorMessage.value = "An error occurred: ${e.message}"
        }
    }
}