package com.example.coroutines

import com.example.coroutines.data.converters.QuoteConverter
import com.example.coroutines.data.converters.TickerQueryConverter
import com.example.coroutines.models.data.QuoteData
import com.example.coroutines.models.data.TickerQueryData
import com.example.coroutines.models.domain.Quote
import com.example.coroutines.models.domain.TickerQuery
import com.google.common.truth.Truth
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class TickerQueryConverterUnitTest {
    @Test
    fun tickerQueryConverterMock_whenCallingMockedMethod_thenCorrectlyVerified() {
        val tickerQueryDataParam = TickerQueryData("3M Company", "Industrials", "MMM")

        val tickerQueryOutput = TickerQuery("3M Company", "Industrials", "MMM")

        // given
        val service = mockk<TickerQueryConverter>()
        every { service.convert(tickerQueryDataParam) } returns tickerQueryOutput

        // result - actual, tickerOutput - expected
        val result = service.convert((tickerQueryDataParam))

        // then
        verify { service.convert(tickerQueryDataParam) }
        Truth.assertThat(result).isEqualTo(tickerQueryOutput)
    }
}