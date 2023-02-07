package com.example.coroutines.data.converters

import com.example.coroutines.models.data.TickerQueryData
import com.example.coroutines.models.domain.TickerQuery
import com.google.common.truth.Truth
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class TickerQueryConverterTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun convert() {
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