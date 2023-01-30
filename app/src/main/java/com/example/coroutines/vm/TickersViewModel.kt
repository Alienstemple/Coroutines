package com.example.coroutines.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutines.MainActivity
import com.example.coroutines.data.Quote
import com.example.coroutines.data.Ticker
import com.example.coroutines.data.TickerOutput
import com.example.coroutines.repository.TickerRepository
import com.example.coroutines.service.NetworkService
import com.example.coroutines.service.TickerService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TickersViewModel : ViewModel() {
    val tickerList = MutableLiveData<List<TickerOutput>>()
    val tickerRepository = TickerRepository(TickerService.getInstance())

    fun testGetTickersAndQuotes(): String {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d(MainActivity.TAG, "coro launched")
            val res1: List<Ticker>
            val res2: List<Quote>
            val call1 = async { tickerRepository.getTicker("AAPL") }
            val call2 = async { tickerRepository.getQuote("AAPL") }
            res1 = call1.await()
            res2 = call2.await()
            // merge to one model
            // TODO tickerList.postValue
        }
        return "OK"
    }

    fun getTickersAndQuotes() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d(MainActivity.TAG, "coro launched")
            val res1: List<Ticker>
            val res2: List<Quote>
            val call1 = async { NetworkService().getTickers() }
            val call2 = async { NetworkService().getQuotes() }

//            when (val response = tickerRepository.getTicker()) {
//                is NetworkState.Success -> {
//                    repoList.postValue(response.data)
//                }
//            }

            res1 = call1.await()
            res2 = call2.await()
//
//            for (i in res1.indices) {
//                tickerOutputRecycler.add(TickerOutput(res1[i].logo,
//                    res1[i].name,
//                    res2[i].c,
//                    res2[i].d,
//                    res2[i].dp))
//            }
//
////                testData = res1.toString() + res2.toString()
////
//            withContext(Dispatchers.Main) {
//                // Update view model
//                tickersViewModel.tickerList.value = tickerOutputRecycler
//                Log.d(MainActivity.TAG, "LiveData updated")
//            }
        }
    }


    fun getTickerAndQuote(ticker: String) {

        viewModelScope.launch {
            Log.d("Thread Inside", Thread.currentThread().name)
//            when (val response = repositoryMain.getUserRepos(userName)) {
//                is NetworkState.Success -> {
//                    repoList.postValue(response.data)
//                }
//                is NetworkState.Error -> {
//                    //movieList.postValue(NetworkState.Error())
//
//                }
//            }
        }
    }

}