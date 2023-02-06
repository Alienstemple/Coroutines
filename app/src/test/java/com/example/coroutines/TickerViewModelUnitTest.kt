package com.example.coroutines

import com.example.coroutines.domain.TickerInteractor
import com.example.coroutines.presentation.vm.TickersViewModel
import com.google.common.truth.Truth
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.junit.MockitoRule

@RunWith(MockitoJUnitRunner::class)
class TickerViewModelUnitTest {
    @Rule
    private val rule: MockitoRule = MockitoJUnit.rule()

    @Mock
    lateinit var tickerInteractor: TickerInteractor  mockk

    lateinit var tickerViewModel: TickersViewModel

    @Before
    fun setUp() {
        tickerInteractor = Mockito.mock(TickerInteractor::class.java)
        this.tickerViewModel = TickersViewModel(this.tickerInteractor)
    }

    @Test
    fun test1() {
//        Mockito.`when`(tickerInteractor.getTickersAndQuotes()).thenReturn()

//        Truth.assertThat().
    }
}