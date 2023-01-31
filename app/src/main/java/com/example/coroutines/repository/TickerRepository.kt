package com.example.coroutines.repository

import com.example.coroutines.data.Quote
import com.example.coroutines.data.Ticker
import com.example.coroutines.repository.service.TickerService

class TickerRepository(private val retrofitService: TickerService) {

    suspend fun getTicker(ticker: String): Ticker? {
        val response = retrofitService.getTickers(ticker, API_KEY)
        return if (response.isSuccessful) {
            response.body()
        } else {
           throw RuntimeException("GetTicker is unsuccessful. Ticker = $ticker")
        }
    }

    suspend fun getQuote(ticker: String): Quote? {
        val response = retrofitService.getQuotes(ticker, API_KEY)
        return if (response.isSuccessful) {
            response.body()
        } else {
            throw RuntimeException("GetQuote is unsuccessful. Ticker = $ticker")
        }
    }

    companion object {
        const val API_KEY = "cf9t7saad3ia9brrlmpgcf9t7saad3ia9brrlmq0"
    }
}