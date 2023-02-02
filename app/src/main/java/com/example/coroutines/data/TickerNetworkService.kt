package com.example.coroutines.data

import android.util.Log
import com.example.coroutines.models.data.QuoteData
import com.example.coroutines.models.data.TickerData

class TickerNetworkService(private val retrofitService: RetrofitService) {

    suspend fun getTicker(ticker: String): TickerData {
        return try {
            val response = retrofitService.getTickers(ticker, API_KEY)

            if (response.isSuccessful && response.body() != null) {
                response.body()!!  // TODO How to avoid
            } else {
                throw RuntimeException("ERROR GetTicket is unsuccessful. Ticker = $ticker")
            }
        } catch (e: java.lang.Exception) {
            Log.d(TAG, "${e.message}")
            TickerData("", "", "", "", "",
                "", 0.0, "Default name", "", 0.0, "Default Ticker", "")
        }
    }

    suspend fun getQuote(ticker: String): QuoteData {
        return try {
            val response = retrofitService.getQuotes(ticker, API_KEY)

            if (response.isSuccessful && response.body() != null) {
                response.body()!!  // TODO How to avoid
            } else {
                throw RuntimeException("ERROR GetQuote is unsuccessful. Ticker = $ticker")
            }
        } catch (e: java.lang.Exception) {
            Log.d(TAG, "${e.message}")
            QuoteData(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0)
        }
    }

    companion object {
        const val TAG = "TickRepoLog"
        const val API_KEY = "cf9t7saad3ia9brrlmpgcf9t7saad3ia9brrlmq0"
    }
}