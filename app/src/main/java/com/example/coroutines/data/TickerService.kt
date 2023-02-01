package com.example.coroutines.data

import com.example.coroutines.models.Quote
import com.example.coroutines.models.Ticker

class TickerService(private val retrofitService: RetrofitService) {

    suspend fun getTicker(ticker: String): Ticker {
        val response = retrofitService.getTickers(ticker, API_KEY)

        if (response.isSuccessful && response.body() != null) {
            return response.body()!!  // TODO How to avoid
        } else {
            throw RuntimeException("ERROR GetTicket is unsuccessful. Ticker = $ticker")
        }
    }

    suspend fun getQuote(ticker: String): Quote {
        val response = retrofitService.getQuotes(ticker, API_KEY)

        if (response.isSuccessful && response.body() != null) {
            return response.body()!!  // TODO How to avoid
        } else {
            throw RuntimeException("ERROR GetQuote is unsuccessful. Ticker = $ticker")
        }
    }

    companion object {
        const val TAG = "TickRepoLog"
        const val API_KEY = "cf9t7saad3ia9brrlmpgcf9t7saad3ia9brrlmq0"
    }
}