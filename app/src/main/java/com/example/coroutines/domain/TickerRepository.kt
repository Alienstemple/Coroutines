package com.example.coroutines.domain

import com.example.coroutines.models.Quote
import com.example.coroutines.models.Ticker
import com.example.coroutines.models.TickerQuery

interface TickerRepository {
    suspend fun getTickerAndQuote(query: TickerQuery): Pair<Ticker, Quote>
}