package com.example.coroutines.data

import android.util.Log
import com.example.coroutines.models.data.QuoteData
import com.example.coroutines.models.data.TickerData

class TickerNetworkService(private val retrofitService: RetrofitService) {

    suspend fun getTicker(ticker: String): TickerData? {
//        return try {  // TODO rm
            val response = retrofitService.getTickers(ticker, API_KEY)

            if (response.isSuccessful && response.body() != null) {

                val resp: TickerData = response.body()!!  // TODO check if fields are null
                checkFieldsNotNull(resp)

                return response.body()!!  // TODO How to avoid
            } else { // TODO оставим
                throw RuntimeException("ERROR GetTicket is unsuccessful. Ticker = $ticker")
            }
//        } catch (e: java.lang.Exception) {
//            Log.d(TAG, "${e.message}")
//            null
////            TickerData("", "", "", "", "",
////                "", 0.0, "Default name", "", 0.0, "Default Ticker", "")
//        }
    }

    private fun checkFieldsNotNull(resp: TickerData) {
        if (resp.country == null)   // TODO check if fields are null
            throw RuntimeException("Field is null!")
    }

    suspend fun getQuote(ticker: String): QuoteData? {
//        return try {
            val response = retrofitService.getQuotes(ticker, API_KEY)
            val resp: QuoteData

            if (response.isSuccessful && response.body() != null) {
                resp = response.body()!!
                return response.body()!!  // TODO How to avoid
            } else {
                throw RuntimeException("ERROR GetQuote is unsuccessful. Ticker = $ticker")
            }
//        } catch (e: java.lang.Exception) {
//            Log.d(TAG, "${e.message}")
//            null
////            QuoteData(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0)
//        }
    }

    companion object {
        const val TAG = "TickRepoLog"
        const val API_KEY = "cf9t7saad3ia9brrlmpgcf9t7saad3ia9brrlmq0"
    }
}