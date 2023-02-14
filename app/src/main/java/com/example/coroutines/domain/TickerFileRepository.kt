package com.example.coroutines.domain

import android.content.Context
import com.example.coroutines.models.domain.TickerQuery

interface TickerFileRepository {
    fun getInputTickers(): List<TickerQuery>
}