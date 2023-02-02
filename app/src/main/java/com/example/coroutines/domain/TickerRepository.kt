package com.example.coroutines.domain

import com.example.coroutines.models.data.QuoteData
import com.example.coroutines.models.data.TickerData
import com.example.coroutines.models.data.TickerQueryData

interface TickerRepository {
    suspend fun getTickerAndQuote(query: TickerQueryData): Pair<TickerData, QuoteData>
}