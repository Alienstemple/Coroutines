package com.example.coroutines.domain

import android.content.Context
import com.example.coroutines.models.domain.Ticker
import com.example.coroutines.models.domain.TickerOutput
import kotlinx.coroutines.flow.Flow

interface TickerInteractor {
    suspend fun getTickersAndQuotes(): List<TickerOutput>
    // stub
    suspend fun getTickerDetails(tickerOutput: TickerOutput): Ticker
    suspend fun getTickersAndQuotesAsFlow(): Flow<List<TickerOutput>>
}