package com.example.coroutines.domain

import com.example.coroutines.models.domain.Quote
import com.example.coroutines.models.domain.Ticker
import com.example.coroutines.models.domain.TickerQuery

interface TickerNetworkRepository {
    suspend fun getTickerAndQuote(query: TickerQuery): Pair<Ticker?, Quote?>?
}