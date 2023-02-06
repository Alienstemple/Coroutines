package com.example.coroutines

import com.example.coroutines.data.converters.TickerOutputConverter
import com.example.coroutines.data.converters.TickerQueryConverter
import com.example.coroutines.models.data.TickerQueryData
import com.example.coroutines.models.domain.Quote
import com.example.coroutines.models.domain.Ticker
import com.example.coroutines.models.domain.TickerOutput
import com.example.coroutines.models.domain.TickerQuery
import com.google.common.truth.Truth
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class TickerOutputConverterUnitTest {
    @Test
    fun tickerOutputConverterMock_whenCallingMockedMethod_thenCorrectlyVerified() {
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