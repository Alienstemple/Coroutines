package com.example.coroutines.data.converters

import com.example.coroutines.models.data.QuoteData
import com.example.coroutines.models.domain.Quote

/**
 * Служебный класс для конвертации модели Quote (стоимость акций) уровня data в модель уровня domain
 */
class QuoteConverter {
    /**
     * Метод, конвертирующий модель Quote уровня data в модель уровня domain
     * @param quoteData Модель QuoteData уровня data
     * @return Возвращает сконвертированный объект Quote уровня domain
     */
    fun convert(quoteData: QuoteData?): Quote? {
        return Quote(
            quoteData?.c,
            quoteData?.d,
            quoteData?.dp,
            quoteData?.h,
            quoteData?.l,
            quoteData?.o,
            quoteData?.pc,
            quoteData?.t)
    }
}