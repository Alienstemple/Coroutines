package com.example.coroutines.domain

import com.example.coroutines.models.Quote
import com.example.coroutines.models.Ticker

interface TickerRepository {
    suspend fun getTickerAndQuote(): Pair<Ticker, Quote>
}