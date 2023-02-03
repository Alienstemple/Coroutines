package com.example.coroutines.data

import com.example.coroutines.data.converters.QuoteConverter
import com.example.coroutines.data.converters.TickerConverter
import com.example.coroutines.domain.TickerNetworkRepository
import com.example.coroutines.models.data.QuoteData
import com.example.coroutines.models.data.TickerData
import com.example.coroutines.models.domain.Quote
import com.example.coroutines.models.domain.Ticker
import com.example.coroutines.models.domain.TickerQuery
import kotlinx.coroutines.async
import kotlinx.coroutines.supervisorScope

class TickerNetworkRepositoryImpl(private val tickerApi: TickerNetworkService) :
    TickerNetworkRepository {

    override suspend fun getTickerAndQuote(query: TickerQuery): Pair<Ticker?, Quote?>? {
        val res1: TickerData?
        val res2: QuoteData?

        supervisorScope {
            val call1 = async { tickerApi.getTicker(query.Symbol) }
            val call2 = async { tickerApi.getQuote(query.Symbol) }

            res1 = kotlin.runCatching { call1.await() }.getOrNull()
            res2 = kotlin.runCatching { call2.await() }.getOrNull()
        }

        if (res1 == null || res2 == null)
            return null
        // return Pair
        return TickerConverter.convert(res1) to QuoteConverter.convert(res2)
    }

    companion object {
        const val TAG = "InterFactLog"
    }
}