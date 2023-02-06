package com.example.coroutines.data.converters

import com.example.coroutines.models.data.TickerQueryData
import com.example.coroutines.models.domain.TickerQuery

class TickerQueryConverter {
    fun convert(tickerData: TickerQueryData): TickerQuery {
        return TickerQuery(
            tickerData.Name,
            tickerData.Sector,
            tickerData.Symbol
        )
    }
}