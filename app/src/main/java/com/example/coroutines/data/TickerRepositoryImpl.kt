package com.example.coroutines.data

import com.example.coroutines.domain.TickerRepository
import com.example.coroutines.models.Quote
import com.example.coroutines.models.Ticker
import com.example.coroutines.models.TickerQuery
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay

class TickerRepositoryImpl : TickerRepository {

    private val tickerApi = TickerService(RetrofitService.getInstance())

    override suspend fun getTickerAndQuote(query: TickerQuery): Pair<Ticker, Quote> = coroutineScope {
        val res1: Ticker
        val res2: Quote
        delay(10)
        val call1 = async { tickerApi.getTicker(query.Symbol) }
        val call2 = async { tickerApi.getQuote(query.Symbol) }

        res1 = call1.await()
        res2 = call2.await()

        // return Pair
        res1 to res2
    }

    companion object {
        const val TAG = "InteractLog"
    }
}