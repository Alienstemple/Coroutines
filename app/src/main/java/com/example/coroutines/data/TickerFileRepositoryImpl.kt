package com.example.coroutines.data

import android.content.Context
import com.example.coroutines.domain.TickerFileRepository
import com.example.coroutines.models.data.TickerQueryData

class TickerFileRepositoryImpl(private val tickerFileService: TickerFileService) : TickerFileRepository {
    override fun getInputTickers(context: Context): List<TickerQueryData> {
        return tickerFileService.getInputTickers(context)
    }
}