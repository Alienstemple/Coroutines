package com.example.coroutines.presentation.vm

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutines.data.OldTickerRepository
import com.example.coroutines.data.RetrofitService
import com.example.coroutines.domain.TickerInteractor
import com.example.coroutines.models.Quote
import com.example.coroutines.models.Ticker
import com.example.coroutines.models.TickerOutput
import com.example.coroutines.models.TickerQuery
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

class TickersViewModel(private val tickerInteractor: TickerInteractor) :
    ViewModel() {
    private val _tickerList = MutableLiveData<List<TickerOutput>>()
    val tickerList: LiveData<List<TickerOutput>> = _tickerList
    private val tickerRepository = OldTickerRepository(RetrofitService.getInstance())

    /**
     * Запускаем асинхронно в coroutineScope с помощью async {}
     */
    suspend fun getTickerAndQuoteFromNetwork(query: TickerQuery): TickerOutput = coroutineScope {
        val res1: Ticker?
        val res2: Quote?
        delay(10)
        val call1 = async { tickerRepository.getTicker(query.Symbol) }
        val call2 = async { tickerRepository.getQuote(query.Symbol) }

        res1 = call1.await()
        res2 = call2.await()
        Log.d(TAG, "In innerGetTickets, coro scope")

        if (res1 != null && res2 != null)
            TickerOutput(res1.logo ?: " ", res1.name ?: " ", res2.c ?: 0.0, res2.d ?: 0.0, res2.dp ?: 0.0)  // TODO fix !!
        else {
            Log.d(TAG, "Got null resp")
            TickerOutput(" ", " ", 0.0, 0.0, 0.0)  // default (not valid) values
        }
    }

    fun getTickersAndQuotes(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            val resultList = tickerInteractor.getTickersAndQuotes(context)
            _tickerList.postValue(resultList)
        }
    }

    fun oldGetTickersAndQuotes(inputList: List<TickerQuery>) {
        Log.d(TAG, "Inp list = $inputList")

        val time = measureTimeMillis {
            viewModelScope.launch(Dispatchers.IO) {
                Log.d(TAG, "coro launched")
                val list: MutableList<TickerOutput> = mutableListOf()
                // Асинхронно запускаем для каждого элемента списка запросы в сеть
                val resultList = inputList.take(30).map {
                    async { getTickerAndQuoteFromNetwork(it) }
                }.awaitAll()
            _tickerList.postValue(resultList)
            }
        }

        Log.d(TAG, "Took time = $time")
    }

    companion object {
        const val TAG = "VMLog"
    }
}
