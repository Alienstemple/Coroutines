package com.example.coroutines.domain

import android.content.Context
import com.example.coroutines.domain.models.TickerOutput

interface TickerInteractor {
    suspend fun getTickersAndQuotes(context: Context): List<TickerOutput>
}