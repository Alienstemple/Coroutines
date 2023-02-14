package com.example.coroutines.data

import android.content.Context
import android.util.Log
import com.example.coroutines.R
import com.example.coroutines.models.data.TickerQueryData
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import javax.inject.Inject

class TickerFileService (private val mapper: ObjectMapper, private val context: Context) {

    fun getInputTickers(): List<TickerQueryData> {
        val inputTickers: String = context.resources.openRawResource(R.raw.s_p_tickers)
            .bufferedReader().use { it.readText() }
        return mapper.readValue(inputTickers, object: TypeReference<List<TickerQueryData>>(){})
    }
}