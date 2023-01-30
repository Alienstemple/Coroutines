package com.example.coroutines.service

import android.util.Log
import com.example.coroutines.data.Quote
import com.example.coroutines.data.Ticket
import com.example.coroutines.data.TicketQuery
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
    private var ticketList: MutableList<Ticket> = mutableListOf()
    private var quoteList: MutableList<Quote> = mutableListOf()
    private var ticketQueryList: List<TicketQuery> = initTicketQueryList()

    fun getTickets(): List<Ticket> {

        ticketQueryList.forEach {
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

            // Jackson from json to Ticket
            ticketList.add(mapper.readValue(response, object : TypeReference<Ticket>() {}))
        }

        return ticketList
    }

    fun getQuotes(): List<Quote> {

        ticketQueryList.forEach {
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

            // Jackson from json to Ticket
            quoteList.add(mapper.readValue(response, object : TypeReference<Quote>() {}))
        }

        return quoteList
    }

    private fun initTicketQueryList(): List<TicketQuery> {
        //        val ticketQueriesJson = File("./s_p_tickers.json").readText(Charsets.UTF_8)  // FIXME ENOENT file not found! Even on device!
        // TODO add File

        val ticketQueriesJson = """
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

        Log.d(TAG, "Json string = $ticketQueriesJson")
        val ticketQuery: List<TicketQuery> = mapper.readValue(ticketQueriesJson)
        Log.d(TAG, "Result list ${ticketQuery.toString()}")
        return ticketQuery
    }

    companion object {
        const val TAG = "NetwServLog"
        const val getCompanyUrl = "https://finnhub.io/api/v1/stock/profile2"
        const val getQuoteUrl = "https://finnhub.io/api/v1/quote"
        const val API_KEY = "cf9t7saad3ia9brrlmpgcf9t7saad3ia9brrlmq0"
    }
}