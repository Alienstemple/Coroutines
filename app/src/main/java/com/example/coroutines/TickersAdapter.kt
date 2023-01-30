package com.example.coroutines

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutines.data.TickerOutput
import com.example.coroutines.databinding.TickerItemBinding
import com.squareup.picasso.Picasso

class TickersAdapter (private val tickersList: List<TickerOutput>): RecyclerView.Adapter<TickersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d(TAG, "onCreateViewHolder() called with: parent = $parent, viewType = $viewType")
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.ticker_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tickersList[position])
    }

    override fun getItemCount() = tickersList.size

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val tickerItemBinding = TickerItemBinding.bind(view)

        fun bind(tickerItem: TickerOutput) = with (tickerItemBinding) {
            Log.d(TAG, "bind() called ${tickerItem.name}")

            tickerNameTv.text = tickerItem.name
            Picasso.get().load("https://static2.finnhub.io/file/publicdatany/finnhubimage/stock_logo/AAPL.svg")  // tickerItem.logo
                .into(tickerIcon)
            cTv.text = tickerItem.c.toString()
            dTv.text = tickerItem.d.toString()
            dpTv.text = tickerItem.dp.toString()
        }
    }

    companion object {
        const val TAG = "TickAdaptLog"
    }
}