package com.example.coroutines.service

import android.util.Log
import com.example.coroutines.data.Quote
import com.example.coroutines.data.Ticker
import com.example.coroutines.data.TickerQuery
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import okhttp3.CookieJar
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class NetworkService {
    private val httpClient = OkHttpClient.Builder()
        .readTimeout(3, TimeUnit.SECONDS)
        .writeTimeout(3, TimeUnit.SECONDS)
        .cookieJar(CookieJar.NO_COOKIES)
        .addNetworkInterceptor(HttpLoggingInterceptor())
        .build()

    private val mapper = jacksonObjectMapper()
    private var tickerList: MutableList<Ticker> = mutableListOf()
    private var quoteList: MutableList<Quote> = mutableListOf()
    private var tickerQueryList: List<TickerQuery> = initTickerQueryList()

    fun getTickers(): List<Ticker> {

        tickerQueryList.forEach {
            val url = getCompanyUrl.toHttpUrl().newBuilder()
                .addQueryParameter("symbol", it.Symbol)
                .addQueryParameter("token", API_KEY)
                .build()

            val request = Request.Builder()
                .url(url)
                .build()

            var response: String = try {
                var resp: Response = httpClient.newCall(request).execute()
                if (resp.isSuccessful) {
                    resp.body?.string() ?: "No content"
                } else {
                    "Response code: ${resp.code}"
                }
            } catch (e: java.lang.Exception) {
                Log.e(TAG, "Get failed!", e)
                e.stackTrace.toString()
            }

            Log.d(TAG, "$response")

            // Jackson from json to Ticker
            tickerList.add(mapper.readValue(response, object : TypeReference<Ticker>() {}))
        }

        return tickerList
    }

    fun getQuotes(): List<Quote> {

        tickerQueryList.forEach {
            val url = getQuoteUrl.toHttpUrl().newBuilder()
                .addQueryParameter("symbol", it.Symbol)
                .addQueryParameter("token", API_KEY)
                .build()

            val request = Request.Builder()
                .url(url)
                .build()

            var response: String = try {
                var resp: Response = httpClient.newCall(request).execute()
                if (resp.isSuccessful) {
                    resp.body?.string() ?: "No content"
                } else {
                    "Response code: ${resp.code}"
                }
            } catch (e: java.lang.Exception) {
                Log.e(TAG, "Get failed!", e)
                e.stackTrace.toString()
            }

            Log.d(TAG, "$response")

            // Jackson from json to Ticker
            quoteList.add(mapper.readValue(response, object : TypeReference<Quote>() {}))
        }

        return quoteList
    }

    private fun initTickerQueryList(): List<TickerQuery> {
        //        val tickerQueriesJson = File("./s_p_tickers.json").readText(Charsets.UTF_8)  // FIXME ENOENT file not found! Even on device!
        // TODO add File

        val tickerQueriesJson = """
[
  {
    "Name": "3M Company",
    "Sector": "Industrials",
    "Symbol": "MMM"
  },
  {
    "Name": "A.O. Smith Corp",
    "Sector": "Industrials",
    "Symbol": "AOS"
  },
  {
    "Name": "Abbott Laboratories",
    "Sector": "Health Care",
    "Symbol": "ABT"
  },
  {
    "Name": "AbbVie Inc.",
    "Sector": "Health Care",
    "Symbol": "ABBV"
  }]
        """

        Log.d(TAG, "Json string = $tickerQueriesJson")
        val tickerQuery: List<TickerQuery> = mapper.readValue(tickerQueriesJson)
        Log.d(TAG, "Result list ${tickerQuery.toString()}")
        return tickerQuery
    }

    companion object {
        const val TAG = "NetwServLog"
        const val getCompanyUrl = "https://finnhub.io/api/v1/stock/profile2"
        const val getQuoteUrl = "https://finnhub.io/api/v1/quote"
        const val API_KEY = "cf9t7saad3ia9brrlmpgcf9t7saad3ia9brrlmq0"
    }
}