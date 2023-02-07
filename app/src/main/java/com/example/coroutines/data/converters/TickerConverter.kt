package com.example.coroutines.data.converters

import com.example.coroutines.models.data.TickerData
import com.example.coroutines.models.domain.Ticker

/**
 * Служебный класс для конвертации модели Ticker (компания) уровня data в модель уровня domain
 */
class TickerConverter {
    /**
     * Метод, конвертирующий модель Ticker уровня data в модель уровня domain
     * @param tickerData Модель TickerData уровня data
     * @return Возвращает сконвертированный объект Ticker уровня domain
     */
    fun convert(tickerData: TickerData?): Ticker? {
        return Ticker(
            country = tickerData?.country,
            currency = tickerData?.currency,
            exchange = tickerData?.exchange,
            finnhubIndustry = tickerData?.finnhubIndustry,
            ipo = tickerData?.ipo,
            logo = tickerData?.logo,
            marketCapitalization = tickerData?.marketCapitalization,
            name = tickerData?.name,
            phone = tickerData?.phone,
            shareOutstanding = tickerData?.shareOutstanding,
            ticker = tickerData?.ticker,
            weburl = tickerData?.weburl)
    }
}