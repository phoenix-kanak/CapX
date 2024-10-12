package com.example.capx.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.SearchView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.capx.common.Constants.API_KEY
import com.example.capx.data.models.Stock
import com.example.capx.databinding.ActivityMainBinding
import com.example.capx.presentation.adapters.GetStocksAdapter
import com.example.capx.presentation.viewmodels.GetStockViewModel
import java.util.Locale


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val stockList: MutableList<Stock> = mutableListOf()
    private val stocksViewModel: GetStockViewModel by viewModels()
    private lateinit var stocksAdapter: GetStocksAdapter
    private lateinit var stocks_rv: RecyclerView
    private lateinit var searchedItems: ArrayList<Stock>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        stocks_rv = binding.tvShowRv
        stocks_rv.layoutManager = LinearLayoutManager(this)
        searchedItems = arrayListOf()
        stocksViewModel.stocks.observe(this) { response ->
            binding.progressBar.visibility = View.INVISIBLE
            Log.d("id123", "$response")

            if (response != null && response.isNotEmpty()) {
                Log.d("id123", "$response")
                stockList.addAll(response)
                searchedItems.addAll(response)
                stocksAdapter = GetStocksAdapter(searchedItems)
                stocks_rv.adapter = stocksAdapter
                stocksAdapter.notifyDataSetChanged()
            }
        }
        stocksViewModel.errorMessage.observe(this) { errorMessage ->
            binding.progressBar.visibility = View.INVISIBLE
            if (errorMessage != null) {
                Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
                Log.d("id123", "$errorMessage")
            }
        }
        getStocks()
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchedItems.clear()
                val searchText = newText!!.lowercase(Locale.getDefault())
                Log.d("search", "$searchText")
                if (searchText.isNotEmpty()) {
                    stockList.forEach {
                        if (it.symbol?.lowercase(Locale.getDefault())?.contains(searchText) == true) {
                            searchedItems.add(it)
                        }
                    }
                    stocksAdapter.notifyDataSetChanged()
                } else {
                    searchedItems.clear()
                    searchedItems.addAll(stockList)
                    stocksAdapter.notifyDataSetChanged()
                }
                return false
            }

        })
    }

    override fun onBackPressed() {
        AlertDialog.Builder(this).setTitle("Exit").setMessage("Are you sure?")
            .setPositiveButton("Yes") { dialog, which ->
                val intent = Intent(Intent.ACTION_MAIN)
                intent.addCategory(Intent.CATEGORY_HOME)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }.setNegativeButton("No") { dialog, which ->
            }.show()
    }

    private fun getStocks() {
        binding.progressBar.visibility = View.VISIBLE
        stocksViewModel.fetchStocks(API_KEY)
    }
}