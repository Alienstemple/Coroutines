package com.example.coroutines.presentation.vm

import com.example.coroutines.domain.TickerInteractor
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class TickersViewModelTest {
    @Rule
    private val rule: MockitoRule = MockitoJUnit.rule()

    @Mock
    lateinit var tickerInteractor: TickerInteractor

    lateinit var tickerViewModel: TickersViewModel

    @Before
    fun setUp() {
        tickerInteractor = Mockito.mock(TickerInteractor::class.java)
        this.tickerViewModel = TickersViewModel(this.tickerInteractor)
    }

    @Test
    fun getTickersAndQuotes() {
    }
}