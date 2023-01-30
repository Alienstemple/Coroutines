package com.example.coroutines

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coroutines.data.Quote
import com.example.coroutines.data.Ticker
import com.example.coroutines.data.TickerOutput
import com.example.coroutines.databinding.ActivityMainBinding
import com.example.coroutines.service.NetworkService
import com.example.coroutines.vm.TickersViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var tickerAdapter: TickersAdapter

    private val tickersViewModel: TickersViewModel by viewModels()

    private var tickerOutputRecycler: MutableList<TickerOutput> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        mainBinding.getBtn.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                Log.d(TAG, "coro launched")
                val testData: String
                val res1: List<Ticker>
                val res2: List<Quote>
                val call1 = async { NetworkService().getTickers() }
                val call2 = async { NetworkService().getQuotes() }

                res1 = call1.await()
                res2 = call2.await()

                for (i in res1.indices) {
                    tickerOutputRecycler.add(TickerOutput(res1[i].logo,
                        res1[i].name,
                        res2[i].c,
                        res2[i].d,
                        res2[i].dp))
                }

//                testData = res1.toString() + res2.toString()
//
                withContext(Dispatchers.Main) {
                    // Update view model
                    tickersViewModel.tickerList.value = tickerOutputRecycler
                    Log.d(TAG, "LiveData updated")
                }
            }

            tickersViewModel.tickerList.observe(this, Observer {
                tickerAdapter = TickersAdapter(it)
                Log.d(TAG, "Tickers: ${it.toString()}")

                mainBinding.tickersRecycler.layoutManager =
                    LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                mainBinding.tickersRecycler.adapter = tickerAdapter
            })
        }

        mainBinding.getRetrofitBtn.setOnClickListener {
            tickersViewModel.testGetTickersAndQuotes()  // сделали асинхронный запрос во view model
            tickersViewModel.tickerList.observe(this, Observer { tickersList ->
                tickersList?.let {
                    // Обновляем Recycler View
                    tickerAdapter = TickersAdapter(it)
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