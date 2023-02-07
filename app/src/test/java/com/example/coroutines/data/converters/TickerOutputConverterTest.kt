package com.example.coroutines.data.converters

import com.example.coroutines.models.domain.Quote
import com.example.coroutines.models.domain.Ticker
import com.example.coroutines.models.domain.TickerOutput
import com.google.common.truth.Truth
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class TickerOutputConverterTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun convert() {
        val tickerParam = Ticker("country",
            "currency",
            "exchange",
            "finnhubIndustry",
            "ipo",
            "logo",
            0.0,
            "name",
            "phone",
            0.0,
            "ticker",
            "weburl")
        val quoteParam = Quote(0.0,
            0.0,
            0.0,
            0.0,
            0.0,
            0.0,
            0.0,
            0.0)

        val tickerOutput = TickerOutput("logo", "name", "symbol", 0.0, 0.0, 0.0)

        // given
        val service = mockk<TickerOutputConverter>()
        every { service.convert(tickerParam, quoteParam) } returns tickerOutput

        // result - actual, tickerOutput - expected
        val result = service.convert(tickerParam, quoteParam)

        // then
        verify { service.convert(tickerParam, quoteParam) }
        Truth.assertThat(result).isEqualTo(tickerOutput)
    }
}