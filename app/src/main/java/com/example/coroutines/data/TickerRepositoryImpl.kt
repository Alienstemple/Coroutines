package com.example.coroutines.data

import com.example.coroutines.domain.TickerRepository
import com.example.coroutines.models.Quote
import com.example.coroutines.models.Ticker
import com.example.coroutines.models.TickerQuery
import kotlinx.coroutines.*

class TickerRepositoryImpl : TickerRepository {

    private val tickerApi = TickerService(RetrofitService.getInstance())

    override suspend fun getTickerAndQuote(query: TickerQuery): Pair<Ticker, Quote> =
        coroutineScope {
            val res1: Ticker
            val res2: Quote

//            val call1 = async { kotlin.runCatching { tickerApi.getTicker(query.Symbol)} }
//            val call2 = async { kotlin.runCatching { tickerApi.getQuote(query.Symbol)} }
//            res1 = call1.await().getOrDefault(Ticker("", "", "", "", "",
//            "", 0.0, "Default name", "", 0.0, "Default Ticker", ""))
//            res2 = call2.await().getOrDefault(Quote(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0))

            val handler: CoroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
                println("Exception thrown in async. $exception")
            }
//            FIXME Хм, тем не менее, наше исключение не обрабатывается в `coroutineExceptionHandler` и приложение падает!
//             Это потому что установка `CoroutineExceptionHandler` в дочерние корутины не имеет никакого эффекта.
//             Мы должны установить обработчик в _scope_ или в корутину верхнего уровня
//            val call1 = async(handler) { tickerApi.getTicker(query.Symbol)}
//            val call2 = async(handler) { tickerApi.getQuote(query.Symbol) }

            val innerScope = CoroutineScope(Job() + handler)
            val call1 = supervisorScope {  innerScope.async { tickerApi.getTicker(query.Symbol)}}
            val call2 = supervisorScope {  innerScope.async { tickerApi.getQuote(query.Symbol)}}
            res1 = call1.await()
            res2 = call2.await()



            // return Pair
            res1 to res2
        }
    // TODO try runCatching and .onSuccess { Log.d(TAG, "OK") }
    //                .onFailure { Log.d(TAG, "Catched exception") }

    companion object {
        const val TAG = "InteractLog"
    }
}