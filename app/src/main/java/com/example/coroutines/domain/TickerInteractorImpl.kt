package com.example.coroutines.domain

import android.content.Context
import android.util.Log
import com.example.coroutines.data.converters.TickerOutputConverter
import com.example.coroutines.models.data.QuoteData
import com.example.coroutines.models.data.TickerData
import com.example.coroutines.models.domain.Quote
import com.example.coroutines.models.domain.Ticker
import com.example.coroutines.models.domain.TickerOutput
import com.example.coroutines.models.domain.TickerQuery
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class TickerInteractorImpl(
    private val tickerNetworkRepository: TickerNetworkRepository,
    private val tickerFileRepository: TickerFileRepository,
) : TickerInteractor {

    override suspend fun getTickersAndQuotesAsFlow(context: Context): Flow<List<TickerOutput>> = supervisorScope {
        Log.d(TAG, "Interactor method getTickersAndQuotesAsFlow called")
        val inputList = tickerFileRepository.getInputTickers(context)

        val tickerFlow: Flow<List<TickerOutput>> = flow {

            while (inputList.isNotEmpty()) {
                Log.d(TAG, "In while")

                // Делаем асинхронный запрос
//                val outputForEmit: List<Pair<Ticker, Quote>> = listOf(Pair(Ticker("country",
//                    "currency",
//                    "exchange",
//                    "finnhubIndustry",
//                    "ipo",
//                    "logo",
//                    0.0,
//                    "name",
//                    "phone",
//                    0.0,
//                    "ticker",
//                    "weburl"), Quote(0.0,
//                    0.0,
//                    0.0,
//                    0.0,
//                    0.0,
//                    0.0,
//                    0.0,
//                    0.0)))
                val outputForEmit = inputList.take(20).mapNotNull {     // take first to avoid network error!
                    Log.d(TAG, "Before calling repo method") // TODO coro можно ли запустить?
                    tickerNetworkRepository.getTickerAndQuote(it)   // Нужно, чтобы в случае ошибки сети этот async был просто отменен (cancelled)
                }

                // emit-им
                emit(outputForEmit.map {
                    TickerOutputConverter().convert(it.first, it.second)
                }).also {
                    inputList.drop(20)  // TODO var, result init
                }
                delay(5000)
            }
        }.flowOn(Dispatchers.IO)  // TODO pass

        return@supervisorScope tickerFlow
    }

    override suspend fun getTickersAndQuotes(context: Context): List<TickerOutput> =
        supervisorScope {
            Log.d(TAG, "Interactor method getTickersAndQuotes called")
            val inputList = tickerFileRepository.getInputTickers(context)

            val outputList = inputList.map {     // take first to avoid network error!
                async { tickerNetworkRepository.getTickerAndQuote(it) }   // Нужно, чтобы в случае ошибки сети этот async был просто отменен (cancelled)
            }.mapNotNull {
                Log.d(TAG, "Map not null called")
                kotlin.runCatching {
                    Log.d(TAG, "RunCatching called. $it")
                    it.await()
                }.getOrNull()
            }

            outputList.map {
                TickerOutputConverter().convert(it.first, it.second)
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