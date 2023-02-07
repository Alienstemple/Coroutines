package com.example.coroutines.data

import android.content.Context
import com.example.coroutines.data.converters.TickerQueryConverter
import com.example.coroutines.domain.TickerFileRepository
import com.example.coroutines.models.data.TickerQueryData
import com.example.coroutines.models.domain.TickerQuery
import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class TickerFileRepositoryImplTest {

    @MockK
    private lateinit var tickerFileService: TickerFileService  // объект-аргумент, передадим в конструктор
    private lateinit var tickerFileRepository: TickerFileRepository  // целевой объект тестирования

    private val context: Context = mockk()  // нужен для передачи во внутренний метод

    //        Готовим входные и выходные данные внутренних методов, эталонные выходные данные нашего репозитория
    private val tickerFileServiceOutputList = listOf(
        TickerQueryData("3M Company", "Industrials", "MMM"),
        TickerQueryData("A.O. Smith Corp", "Industrials", "AOS")
    )

    private val tickerFileRepositoryOutputList = listOf(
        TickerQuery("3M Company", "Industrials", "MMM"),
        TickerQuery("A.O. Smith Corp", "Industrials", "AOS")
    )

    @Before
    fun setUp() {
        MockKAnnotations.init(this)  // Инициализируем все, что под аннотацией @MockK
        this.tickerFileRepository = TickerFileRepositoryImpl(tickerFileService)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getInputTickers() {
        // every - заглушка для внутренних методов, в нашем случае - tickerFileService
        every { tickerFileService.getInputTickers(context) } returns tickerFileServiceOutputList

        // when
        val result = tickerFileRepository.getInputTickers(context)

        // then
        verify { tickerFileService.getInputTickers(context) }
        Truth.assertThat(result).isEqualTo(tickerFileRepositoryOutputList)
    }
}