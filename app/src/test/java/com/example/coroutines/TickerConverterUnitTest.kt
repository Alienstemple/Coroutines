package com.example.coroutines

import com.example.coroutines.data.converters.TickerConverter
import com.example.coroutines.models.data.TickerData
import com.example.coroutines.models.domain.Ticker
import com.google.common.truth.Truth
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert
import org.junit.Test

class TickerConverterUnitTest {

    @Test
    fun tickerConverterMock_whenCallingMockedMethod_thenCorrectlyVerified() {
        val tickerDataParam = TickerData("country",
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

        val tickerOutput = Ticker("country",
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

        // given
        val service = mockk<TickerConverter>()
        every { service.convert(tickerDataParam) } returns tickerOutput

        // result - actual, tickerOutput - expected
        val result = service.convert((tickerDataParam))

        // then
        verify { service.convert(tickerDataParam) }
        Truth.assertThat(result).isEqualTo(tickerOutput)
    }
}