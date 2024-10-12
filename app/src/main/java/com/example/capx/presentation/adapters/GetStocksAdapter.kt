package com.example.capx.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.capx.R
import com.example.capx.data.models.Stock
import com.example.capx.databinding.StockItemBinding
import com.squareup.picasso.Picasso

class GetStocksAdapter(private var list: List<Stock>) :
    RecyclerView.Adapter<GetStocksAdapter.ViewHolder>() {
    class ViewHolder(binding: StockItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val stockName: TextView = binding.stockName
        val stockSymbol: TextView = binding.stockSymbol
        val stockPrice: TextView = binding.stockPrice
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = StockItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val stocks = list[position]
        holder.stockName.text = stocks.name
        holder.stockSymbol.text=stocks.symbol
        holder.stockPrice.text= "$"+stocks.price.toString()
    }
}