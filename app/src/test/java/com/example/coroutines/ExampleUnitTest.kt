package com.example.coroutines

import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mock

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class ExampleUnitTest {
    @Mock
    val a: String = ""

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}