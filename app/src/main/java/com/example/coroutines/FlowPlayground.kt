package com.example.coroutines

import com.example.coroutines.data.converters.TickerQueryConverter
import com.example.coroutines.models.domain.TickerQuery
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.junit.Test

@ExperimentalStdlibApi
class FlowPlayground {

    @Test
    fun testFlow() {
        val input: List<TickerQuery> = listOf(
            TickerQuery("name1", "sector1", "symbol1"),
            TickerQuery("name2", "sector2", "symbol2"),
            TickerQuery("name3", "sector3", "symbol3"),
            TickerQuery("name4", "sector4", "symbol4"),
            TickerQuery("name5", "sector5", "symbol5"),
            TickerQuery("name6", "sector6", "symbol6"),
            TickerQuery("name7", "sector7", "symbol7"),
            TickerQuery("name8", "sector8", "symbol8")
        )
        val testFlow = flow {
            while (true) {
                CoroutineScope(Dispatchers.IO)
                emit(getModels(input.take(2))).also {
                    input.drop(2)
                }
                if (input.isEmpty())
                    break
                delay(1000)
            }
        }.flowOn(Dispatchers.IO)

//        testFlow.collect

        Thread.sleep(10000)
    }

    private fun getModels(input: List<TickerQuery>): List<String> {
        println("Answer for $input")
        return input.map { "Answer for ${it.Symbol}" }
    }
}