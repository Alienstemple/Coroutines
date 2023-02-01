package com.example.coroutines.domain

import android.content.Context
import com.example.coroutines.models.TickerOutput

interface TickerInteractor {
    suspend fun getTickersAndQuotes(context: Context): List<TickerOutput>
}