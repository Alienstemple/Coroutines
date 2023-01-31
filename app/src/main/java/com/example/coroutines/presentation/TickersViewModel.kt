package com.example.coroutines.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutines.models.Quote
import com.example.coroutines.models.Ticker
import com.example.coroutines.models.TickerOutput
import com.example.coroutines.models.TickerQuery
import com.example.coroutines.data.TickerRepository
import com.example.coroutines.data.RetrofitService
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

class TickersViewModel(/*private val tickerRepository: TickerRepository*/) :
    ViewModel() {  // TODO  Fabric
    private val _tickerList = MutableLiveData<List<TickerOutput>>()
    val tickerList: LiveData<List<TickerOutput>> = _tickerList
    private val tickerRepository = TickerRepository(RetrofitService.getInstance())

    /**
     * Запускаем асинхронно в coroutineScope с пом async {}
     */
    private suspend fun innerGetTickers(query: TickerQuery)/*: TickerOutput*/ = coroutineScope {
        var res1: Ticker?
        var res2: Quote?
        delay(10)
        val call1 = async { tickerRepository.getTicker(/*"AAPL"*/query.Symbol) }
        val call2 = async { tickerRepository.getQuote(/*"AAPL"*/query.Symbol) }

        res1 = call1.await()
        res2 = call2.await()
        Log.d(TAG, "In innerGetTickets, coro scope")

        // return
//        TickerOutput(res1!!.logo, res1.name, res2!!.c, res2.d, res2.dp)  // TODO fix !!
    }

    fun testGetTickersAndQuotes(inputList: List<TickerQuery>) {
        Log.d(TAG, "Inp list = $inputList")

        val time = measureTimeMillis {
            viewModelScope.launch(Dispatchers.IO) {
                Log.d(TAG, "coro launched")
                val list: MutableList<TickerOutput> = mutableListOf()
                // TODO return TickerOutput!
//                inputList.map {
//                    async { innerGetTickers(it) }
//                }.awaitAll()
                inputList.forEach {  // Последовательно запускаем, чтобы не подумал, что это DDoS
                    innerGetTickers(it)
                }
//            _tickerList.postValue(list)
            }
        }

        Log.d(TAG, "Took time = $time")
    }

    companion object {
        const val TAG = "VMLog"
    }
}
