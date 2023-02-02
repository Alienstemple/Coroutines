package com.example.coroutines.data

import android.content.Context
import com.example.coroutines.data.converters.TickerOutputConverter
import com.example.coroutines.domain.TickerFileRepository
import com.example.coroutines.models.domain.TickerQuery

class TickerFileRepositoryImpl(private val tickerFileService: TickerFileService) : TickerFileRepository {
    override fun getInputTickers(context: Context): List<TickerQuery> {
        val inputTickersData = tickerFileService.getInputTickers(context)
        return inputTickersData.map { TickerOutputConverter.convert(it) }
    }
}