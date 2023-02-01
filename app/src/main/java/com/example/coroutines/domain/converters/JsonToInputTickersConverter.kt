package com.example.coroutines.domain.converters

import android.content.Context
import com.example.coroutines.R
import com.example.coroutines.models.TickerQuery
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

object JsonToInputTickersConverter {
    private val mapper = jacksonObjectMapper()

    fun getInputTickers(context: Context): List<TickerQuery> {
        val inputTickers: String = context.resources.openRawResource(R.raw.s_p_tickers)
        .bufferedReader().use { it.readText() }
        return mapper.readValue(inputTickers, object: TypeReference<List<TickerQuery>>(){})
    }

}