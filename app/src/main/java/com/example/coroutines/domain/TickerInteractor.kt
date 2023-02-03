package com.example.coroutines.domain

import android.content.Context
import com.example.coroutines.models.domain.Ticker
import com.example.coroutines.models.domain.TickerOutput

interface TickerInteractor {
    suspend fun getTickersAndQuotes(context: Context): List<TickerOutput>

    // stub
    suspend fun getTickerDetails(tickerOutput: TickerOutput): Ticker
}