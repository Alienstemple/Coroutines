package com.example.coroutines.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutines.MainActivity
import com.example.coroutines.R
import com.example.coroutines.data.Quote
import com.example.coroutines.data.Ticker
import com.example.coroutines.data.TickerOutput
import com.example.coroutines.data.TickerQuery
import com.example.coroutines.repository.NetworkState
import com.example.coroutines.repository.TickerRepository
import com.example.coroutines.repository.service.NetworkService
import com.example.coroutines.repository.service.TickerService
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TickersViewModel(/*private val tickerRepository: TickerRepository*/) :
    ViewModel() {  // TODO  Fabric
    private val _tickerList = MutableLiveData<List<TickerOutput>>()
    val tickerList: LiveData<List<TickerOutput>> = _tickerList
    private val tickerRepository = TickerRepository(TickerService.getInstance())

    fun testGetTickersAndQuotes(inputList: List<TickerQuery>) {
        Log.d(TAG, "Inp list = $inputList")

        viewModelScope.launch(Dispatchers.IO) {
            Log.d(TAG, "coro launched")
            val list: MutableList<TickerOutput> = mutableListOf()

//            inputList.forEach {  // TODO foreach
            var res1: Ticker?
            var res2: Quote?
            val call1 = async { tickerRepository.getTicker("AAPL"/*it.Symbol*/) }
            val call2 = async { tickerRepository.getQuote("AAPL"/*it.Symbol*/) }

            res1 = call1.await()
            res2 = call2.await()

            // TODO to interactor

            if (res1 != null) {
                if (res2 != null) {
                    list.add(TickerOutput(res1.logo, res1.name, res2.c, res2.d, res2.dp))
                }
            }
//            }
            _tickerList.postValue(list)
        }
    }

    companion object {
        const val TAG = "VMLog"
    }
}
