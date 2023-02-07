package com.example.coroutines.data

import android.content.Context
import com.example.coroutines.models.data.TickerQueryData
import com.fasterxml.jackson.databind.ObjectMapper
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class TickerFileServiceTest {

    //  Протестим TickerFileService, понадобится object mapper
    private var mapper: ObjectMapper = mockk()
    private val context = mockk<Context>(relaxed = true)  // IO exception - zero bytes

    // Объявим наш TickerFileService
    private lateinit var tickerFileService: TickerFileService

    @Before
    fun setup() {
        // Проинициализируем здесь наш TickerFileService
        tickerFileService = TickerFileService(mapper)
//        mockkStatic()  // Статику мокаем и очищаем
//        mockkObject() // Статические объекты мокаем так
    }

    @After
    fun clean() {
//        unmockkStatic()
    }

    @Test
    fun getInputTickers() {

        val tickerQueryList: List<TickerQueryData> = emptyList()

        // TODO как замокать контекст?
        every { tickerFileService.getInputTickers(context) } returns tickerQueryList

        // Ожидаемый результат

        // Реальный результат

        // Truth.assertThat.isEqualTo

        // verify Вызов внутреннего метода происходит ровно  раз
        verify(exactly = 1){}
    }
}