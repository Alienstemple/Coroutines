package com.example.coroutines

import com.example.coroutines.data.RetrofitService
import com.example.coroutines.data.TickerNetworkService
import com.example.coroutines.data.converters.TickerConverter
import com.example.coroutines.models.data.TickerData
import com.example.coroutines.models.domain.Ticker
import com.fasterxml.jackson.annotation.JsonProperty
import io.mockk.*
import kotlinx.coroutines.channels.ticker
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class ExampleUnitTest {

    //  Протестим TickerNetworkService, понадобится RetrofitService
    private var retrofitService: RetrofitService = mockk()
    // Объявим наш TickerNetworkService
    private lateinit var tickerNetworkService: TickerNetworkService

    @Before
    fun setup() {
        // Проинициализируем здесь наш TickerNetworkService
        tickerNetworkService = TickerNetworkService(retrofitService)
//        mockkStatic()  // Статику мокаем и очищаем
    }

    @After
    fun clean() {
//        unmockkStatic()
    }

    @Test
    fun test() {
        // TODO test coroutines
//        every { tickerNetworkService.getTicker("test") } returns TickerData()
    }
}