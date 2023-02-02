package com.example.coroutines.data.converters

import com.example.coroutines.models.data.TickerData
import com.example.coroutines.models.domain.Ticker

object TickerConverter {
    fun convert(tickerData: TickerData): Ticker {
        return Ticker(
            tickerData.country,
            tickerData.currency,
            tickerData.exchange,
            tickerData.finnhubIndustry,
            tickerData.ipo,
            tickerData.logo,
            tickerData.marketCapitalization,
            tickerData.name,
            tickerData.phone,
            tickerData.shareOutstanding,
            tickerData.ticker,
            tickerData.weburl)
    }
}