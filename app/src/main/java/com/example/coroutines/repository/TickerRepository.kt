package com.example.coroutines.repository

import com.example.coroutines.data.Quote
import com.example.coroutines.data.Ticker
import com.example.coroutines.service.TickerService

class TickerRepository(private val retrofitService: TickerService) {

    suspend fun getTicker(ticker: String): NetworkState<Ticker> {
        val response = retrofitService.getTickers(ticker, API_KEY)
        return if (response.isSuccessful) {
            val responseBody = response.body()
            if (responseBody != null) {
                NetworkState.Success(responseBody)
            } else {
                NetworkState.Error(response)
            }
        } else {
            NetworkState.Error(response)
        }
    }

    suspend fun getQuote(ticker: String): NetworkState<Quote> {
        val response = retrofitService.getQuotes(ticker, API_KEY)
        return if (response.isSuccessful) {
            val responseBody = response.body()
            if (responseBody != null) {
                NetworkState.Success(responseBody)
            } else {
                NetworkState.Error(response)
            }
        } else {
            NetworkState.Error(response)
        }
    }

    companion object {
        const val API_KEY = "cf9t7saad3ia9brrlmpgcf9t7saad3ia9brrlmq0"
    }
}