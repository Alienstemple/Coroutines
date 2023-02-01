package com.example.coroutines.data

import android.util.Log
import com.example.coroutines.models.Quote
import com.example.coroutines.models.Ticker

class OldTickerRepository(private val retrofitService: RetrofitService) {

    suspend fun getTicker(ticker: String): Ticker? {
        val response = retrofitService.getTickers(ticker, API_KEY)
        return if (response.isSuccessful) {
            Log.d(TAG,"GetTicker is successful. Ticker = $ticker")
            response.body()
        } else {
            Log.d(TAG, " ERROR GetTicker is unsuccessful. Ticker = $ticker")
            null
        }
    }

    suspend fun getQuote(ticker: String): Quote? {
        val response = retrofitService.getQuotes(ticker, API_KEY)
        return if (response.isSuccessful) {
            Log.d(TAG,"GetQuote is successful. Ticker = $ticker")
            response.body()
        } else {
            Log.d(TAG, " ERROR GetQuote is unsuccessful. Ticker = $ticker")
            null
        }
    }

    companion object {
        const val TAG = "TickRepoLog"
        const val API_KEY = "cf9t7saad3ia9brrlmpgcf9t7saad3ia9brrlmq0"
    }
}