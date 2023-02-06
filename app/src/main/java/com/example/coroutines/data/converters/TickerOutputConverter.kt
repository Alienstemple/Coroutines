package com.example.coroutines.data.converters

import com.example.coroutines.models.domain.Quote
import com.example.coroutines.models.domain.Ticker
import com.example.coroutines.models.domain.TickerOutput

object TickerOutputConverter {



    fun convert(ticker: Ticker?, quote: Quote?): TickerOutput {
        return TickerOutput(
            ticker?.logo,
            ticker?.name,
            ticker?.ticker,
            quote?.c,
            quote?.d,
            quote?.dp
        )
    }
}
