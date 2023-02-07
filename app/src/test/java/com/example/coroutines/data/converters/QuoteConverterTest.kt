package com.example.coroutines.data.converters

import com.example.coroutines.models.data.QuoteData
import com.example.coroutines.models.domain.Quote
import com.google.common.truth.Truth
import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * Тестовый класс,
 * проверяет корректность конвертации экземпляра Quote уровня data
 * в экземпляр уровня domain
 */
class QuoteConverterTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    /**
     * Метод для тестирования конвертации экземпляра Quote уровня data
     * в экземпляр уровня domain.
     * [quoteDataParam] передаем на вход конвертеру
     * [quoteOutput] ожидаемый выход
     * [service] экземпляр конвертера
     * [result] реальный результат конвертации
     */
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