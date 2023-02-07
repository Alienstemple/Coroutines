package com.example.coroutines

import com.example.coroutines.data.converters.QuoteConverter
import com.example.coroutines.models.data.QuoteData
import com.example.coroutines.models.domain.Quote
import com.google.common.truth.Truth
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class QuoteConverterUnitTest {
    @Test
    fun quoteConverterMock_whenCallingMockedMethod_thenCorrectlyVerified() {
        val quoteDataParam = QuoteData(0.0,
            0.0,
            0.0,
            0.0,
            0.0,
            0.0,
            0.0,
            0.0)

        val quoteOutput = Quote(0.0,
            0.0,
            0.0,
            0.0,
            0.0,
            0.0,
            0.0,
            0.0)

        // given
        val service = QuoteConverter()   // FIXME error unless mock
//        val service = mockk<QuoteConverter>()
//        every { service.convert(quoteDataParam) } returns quoteOutput

        // result - actual, tickerOutput - expected
        val result = service.convert((quoteDataParam))

        // then
//        verify { service.convert(quoteDataParam) }
        Truth.assertThat(result).isEqualTo(quoteOutput)
    }
}