package com.example.coroutines.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coroutines.R
import com.example.coroutines.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var tickerAdapter: TickersAdapter

    private val tickersViewModel: TickersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        // Init adapter for recycler
        tickerAdapter = TickersAdapter()
        mainBinding.tickersRecycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mainBinding.tickersRecycler.adapter = tickerAdapter

        // Init observer
        tickersViewModel.tickerList.observe(this, Observer { tickersList ->
            tickersList?.let {
                // Обновляем Recycler View
                tickerAdapter.setList(it)
            }
        })

        val inputList = JsonToInputTickersConverter.getInputTickers(this)

        mainBinding.getRetrofitBtn.setOnClickListener {
            tickersViewModel.getTickersAndQuotes(inputList)  // сделали асинхронный запрос во view model
        }
    }

    companion object {
        const val TAG = "MainActLog"
    }
}