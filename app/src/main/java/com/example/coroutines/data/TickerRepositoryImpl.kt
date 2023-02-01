package com.example.coroutines.data

import android.util.Log
import com.example.coroutines.domain.TickerRepository
import com.example.coroutines.models.Quote
import com.example.coroutines.models.Ticker
import com.example.coroutines.models.TickerQuery
import kotlinx.coroutines.*

class TickerRepositoryImpl : TickerRepository {

    private val tickerApi = TickerService(RetrofitService.getInstance())

    override suspend fun getTickerAndQuote(query: TickerQuery): Pair<Ticker, Quote> =
        coroutineScope {
            val res1: Ticker
            val res2: Quote
            delay(10)

            val handler: CoroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
                println("Handler in async. $exception")
            }

            val call1 = async(SupervisorJob() + handler) { tickerApi.getTicker(query.Symbol) }
            val call2 = async(SupervisorJob() + handler) { tickerApi.getQuote(query.Symbol) }
            res1 = call1.await()
            res2 = call2.await()
            // return Pair
            res1 to res2
        }
    // TODO try runCatching and .onSuccess { Log.d(TAG, "OK") }
    //                .onFailure { Log.d(TAG, "Catched exception") }

    companion object {
        const val TAG = "InteractLog"
    }
}