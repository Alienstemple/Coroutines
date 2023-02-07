package com.example.coroutines.data.converters

import com.example.coroutines.models.domain.Quote
import com.example.coroutines.models.domain.Ticker
import com.example.coroutines.models.domain.TickerOutput

/**
 * Служебный класс для конвертации моделей Ticker и Quote (компания и стоимость ее акций),
 * полученных из репозитория, в объединенную модель, передаваемую на уровень представления
 */
class TickerOutputConverter {
    /**
     * Метод, конвертирующий модели Ticker и Quote (компания и стоимость ее акций),
     * полученные из репозитория, в объединенную модель, передаваемую на уровень представления
     * @param ticker Модель Ticker уровня data
     * @param quote Модель Quote уровня data
     * @return Возвращает сконвертированный объект TickerOutput уровня domain
     */
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
