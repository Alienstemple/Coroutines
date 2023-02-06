package com.example.coroutines

import com.example.coroutines.data.TickerNetworkRepositoryImpl
import com.example.coroutines.data.TickerNetworkService
import com.example.coroutines.domain.TickerNetworkRepository
import com.example.coroutines.models.domain.TickerQuery
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class TickerNetworkRepositoryUnitTest {

    @MockK
    lateinit var tickerApi: TickerNetworkService
    lateinit var tickerNetworkRepository: TickerNetworkRepository

    private val tickerQuery: TickerQuery = TickerQuery("3M Company", "Industrials", "MMM")
//    private val tickerAndQuote: Pair<Ticker, Quote> = Ticker() to Quote()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        // Инициализируем репозиторий, передаем mockk-объект в конструктор
        this.tickerNetworkRepository = TickerNetworkRepositoryImpl(tickerApi)
    }

    @Test
    fun testExecute_Positive() = runBlocking {
        // Эмулируем вызов внутренних методов
        coEvery { /*tickerApi.getTicker(Symbol), getQuote*/ } // returns

        val tickerAndQuoteResult = tickerNetworkRepository.getTickerAndQuote(tickerQuery)

    }
}