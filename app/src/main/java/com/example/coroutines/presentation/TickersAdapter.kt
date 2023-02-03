package com.example.coroutines.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutines.R
import com.example.coroutines.models.domain.TickerOutput
import com.example.coroutines.databinding.TickerItemBinding
import com.squareup.picasso.Picasso

class TickersAdapter(private val itemClickListener: MyItemClickListener): RecyclerView.Adapter<TickersAdapter.ViewHolder>() {

    private val tickersList = mutableListOf<TickerOutput>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d(TAG, "onCreateViewHolder() called with: parent = $parent, viewType = $viewType")

        val binding = TickerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tickersList[position], itemClickListener)
    }

    override fun getItemCount() = tickersList.size

    fun setList(newList: List<TickerOutput>) {
        tickersList.apply {
            clear()
            addAll(newList)
        }
        notifyDataSetChanged()
    }

     class ViewHolder(binding: TickerItemBinding): RecyclerView.ViewHolder(binding.root) {

        private val tickerItemBinding = binding

        fun bind(tickerItem: TickerOutput, clickListener: MyItemClickListener) = with (tickerItemBinding) {
            Log.d(TAG, "bind() called ${tickerItem.name}")

            tickerNameTv.text = tickerItem.name
            Picasso.get().load("https://static2.finnhub.io/file/publicdatany/finnhubimage/stock_logo/AAPL.svg")  // tickerItem.logo
                .into(tickerIcon)
            cTv.text = tickerItem.c.toString()
            dTv.text = tickerItem.d.toString()
            dpTv.text = tickerItem.dp.toString()

            itemView.setOnClickListener {
                clickListener.onItemClicked(tickerItem)
            }
        }
    }

    companion object {
        const val TAG = "TickAdaptLog"
    }
}