package com.example.coroutines.utils

import android.util.Log
import com.example.coroutines.models.data.TickerQueryData
import com.example.coroutines.models.domain.TickerOutput
import com.example.coroutines.models.domain.TickerQuery
import kotlin.test.assertFailsWith
import com.google.common.truth.Truth
import io.mockk.impl.annotations.MockK
import org.junit.After
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class MyParserTest {

    private val myParser = MyParser(TickerQueryData::class.java)

    @Before
    fun setup() {

    }

    @After
    fun clean() {

    }

    @Test
    fun parse() {  // test parse ticker query
        val input = """ 
              {
                "Name": "A.O. Smith Corp",
                "Sector": "Industrials",
                "Symbol": "AOS"
              }
        """.trimIndent()

        val output = TickerQueryData("A.O. Smith Corp", "Industrials", "AOS")

        val result = myParser.parse(input)

        Truth.assertThat(result).isEqualTo(output)

    }

    @Test
    fun parseFile() {
        val output = TickerQueryData("A.O. Smith Corp", "Industrials", "AOS")

        val result = myParser.parseFile("example_for_parser.json")

        Truth.assertThat(result).isEqualTo(output)
    }

    @Test
    fun `convert file with wrong name gives an Exception`() {

        assertFailsWith<IllegalStateException> {
            myParser.parseFile("example_for.json")
        }
    }
}