package com.example.coroutines.domain

import android.content.Context
import android.util.Log
import com.example.coroutines.domain.converters.JsonToInputTickersConverter
import com.example.coroutines.models.Quote
import com.example.coroutines.models.Ticker
import com.example.coroutines.domain.models.TickerOutput
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class TickerInteractorImpl(private val tickerRepository: TickerRepository) : TickerInteractor {

    override suspend fun getTickersAndQuotes(context: Context): List<TickerOutput> =
        coroutineScope {
            Log.d(TAG, "Test interact method called")
            val inputList = JsonToInputTickersConverter.getInputTickers(context)

            val outputList: List<Pair<Ticker, Quote>> = inputList.map {     // take first to avoid network error!
                async { tickerRepository.getTickerAndQuote(it) }   // Нужно, чтобы в случае ошибки сети этот async был просто отменен (cancelled)
            }.awaitAll()

            outputList.map {
                TickerOutput(it.first.logo ?: " ", it.first.name ?: " ",
                    it.second.c ?: 0.0, it.second.d ?: 0.0, it.second.dp ?: 0.0)  // TODO fix elvis !!
            }.filter { it.name != "Default name" && it.name != "" && it.c != 0.0 }
        }

    companion object {
        const val TAG = "InteractLog"
    }
}