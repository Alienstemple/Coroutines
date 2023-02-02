package com.example.coroutines.domain

import android.content.Context
import com.example.coroutines.models.data.TickerQueryData

interface TickerFileRepository {
    fun getInputTickers(context: Context): List<TickerQueryData>
}