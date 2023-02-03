package com.example.coroutines.domain

import android.content.Context
import android.util.Log
import com.example.coroutines.models.data.QuoteData
import com.example.coroutines.models.data.TickerData
import com.example.coroutines.models.domain.Quote
import com.example.coroutines.models.domain.Ticker
import com.example.coroutines.models.domain.TickerOutput
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.supervisorScope

class TickerInteractorImpl(
    private val tickerNetworkRepository: TickerNetworkRepository,
    private val tickerFileRepository: TickerFileRepository,
) : TickerInteractor {

    override suspend fun getTickersAndQuotes(context: Context): List<TickerOutput> =
        supervisorScope {
            Log.d(TAG, "Test interact method called")
            val inputList = tickerFileRepository.getInputTickers(context)

            val outputList = inputList.map {     // take first to avoid network error!
                async { tickerNetworkRepository.getTickerAndQuote(it) }   // Нужно, чтобы в случае ошибки сети этот async был просто отменен (cancelled)
            }.mapNotNull {
                Log.d(TAG, "Map not null called")
                kotlin.runCatching {
                    Log.d(TAG, "RunCatching called. ${it}")
                    it.await()
                }.getOrNull()
            }

            outputList.map {
                TickerOutput(it.first?.logo ?: " ",
                    it.first?.name ?: " ",
                    it.second?.c ?: 0.0,
                    it.second?.d ?: 0.0,
                    it.second?.dp ?: 0.0)  // TODO fix elvis !!
            }
        }

    override suspend fun getTickerDetails(tickerOutput: TickerOutput): Ticker {
        Log.d(TAG, "Interactor called getTickerDetailes")
        // TODO call repo method
        val resTicker = Ticker("Example Country",
            "Example Currency",
            "Example Exchange",
            "Example Ind",
            "Example Ipo",
            "logo",
            0.0,
            "Name",
            "",
            0.0,
            "AAPL",
            "http//")
        return resTicker

    }

    companion object {
        const val TAG = "InteractLog"
    }
}