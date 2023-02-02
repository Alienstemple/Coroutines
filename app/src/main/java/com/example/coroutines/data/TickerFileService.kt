package com.example.coroutines.data

import android.content.Context
import com.example.coroutines.R
import com.example.coroutines.models.data.TickerQueryData
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

class TickerFileService {
    private val mapper = jacksonObjectMapper()

    fun getInputTickers(context: Context): List<TickerQueryData> {
        val inputTickers: String = context.resources.openRawResource(R.raw.s_p_tickers)
            .bufferedReader().use { it.readText() }
        return mapper.readValue(inputTickers, object: TypeReference<List<TickerQueryData>>(){})
    }
}