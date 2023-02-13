package com.example.coroutines.data

import android.content.Context
import com.example.coroutines.data.converters.TickerQueryConverter
import com.example.coroutines.domain.TickerFileRepository
import com.example.coroutines.models.domain.TickerQuery
import javax.inject.Inject

/**
 * Класс для получения списка входных моделей Ticker из файла s_p_tickers.json
 * @constructor Принимает на вход [tickerFileService] - сервис для чтения и конвертации json-файла
 */
class TickerFileRepositoryImpl @Inject constructor(private val tickerFileService: TickerFileService) : TickerFileRepository {
    /**
     * Метод для получения списка входных моделей Ticker из файла s_p_tickers.json
     * @param context Контекст MainActivity
     * @return Список моделей для сетевого запроса List<TickerQuery>
     */
    override fun getInputTickers(context: Context): List<TickerQuery> {
        val inputTickersData = tickerFileService.getInputTickers(context)
        return inputTickersData.map { TickerQueryConverter().convert(it) }
    }
}