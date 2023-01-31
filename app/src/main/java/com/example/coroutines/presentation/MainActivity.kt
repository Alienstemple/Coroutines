package com.example.coroutines.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coroutines.R
import com.example.coroutines.databinding.ActivityMainBinding
import com.example.coroutines.repository.service.JsonToInputTickersConverter

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var tickerAdapter: TickersAdapter

    private val tickersViewModel: TickersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        val inputTickers: String = resources.openRawResource(R.raw.s_p_tickers)
            .bufferedReader().use { it.readText() }
        Log.d(TAG, "input = $inputTickers")


        // TODO observe, init adapter

        mainBinding.getRetrofitBtn.setOnClickListener {
            val inputList = JsonToInputTickersConverter.getInputTickers(this)
            tickersViewModel.testGetTickersAndQuotes(inputList)  // сделали асинхронный запрос во view model
            tickersViewModel.tickerList.observe(this, Observer { tickersList ->
                tickersList?.let {
                    // Обновляем Recycler View
                    tickerAdapter = TickersAdapter(it) // TODO pass list
                    Log.d(TAG, "Tickers: ${it.toString()}")

                    mainBinding.tickersRecycler.layoutManager =
                        LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                    mainBinding.tickersRecycler.adapter = tickerAdapter
                }

            })
        }
    }

    companion object {
        const val TAG = "MainActLog"
    }
}