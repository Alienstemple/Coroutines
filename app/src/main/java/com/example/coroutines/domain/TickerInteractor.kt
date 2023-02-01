package com.example.coroutines.domain

import android.content.Context
import com.example.coroutines.models.TickerOutput

interface TickerInteractor {
    fun getTickersAndQuotes(context: Context): List<TickerOutput>
}