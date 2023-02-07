package com.example.coroutines.data.converters

import com.example.coroutines.models.data.QuoteData
import com.example.coroutines.models.domain.Quote
import com.google.common.truth.Truth
import org.junit.After
import org.junit.Before
import org.junit.Test

class QuoteConverterTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun convert() {
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

        val service = QuoteConverter()

        val result = service.convert((quoteDataParam))

        Truth.assertThat(result).isEqualTo(quoteOutput)
    }
}