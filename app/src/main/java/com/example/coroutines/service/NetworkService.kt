package com.example.coroutines.service

import android.util.Log
import com.example.coroutines.data.Ticket
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
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
    private var ticketList: List<Ticket> = emptyList()

    fun getTickets(): String /*List<Ticket>*/ {

        val url = getCompanyUrl.toHttpUrl().newBuilder()
            .addQueryParameter("symbol", "AAPL")
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

        // Jackson from json to List
//        pictureList = mapper.readValue(response, object : TypeReference<List<Picture>>() {})

        return response
    }

    fun getQuotes(): String /*List<Ticket>*/ {

        val url = getQuoteUrl.toHttpUrl().newBuilder()
            .addQueryParameter("symbol", "AAPL")
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

        // Jackson from json to List
//        pictureList = mapper.readValue(response, object : TypeReference<List<Picture>>() {})

        return response
    }

    companion object {
        const val TAG = "NetwServLog"
        const val getCompanyUrl = "https://finnhub.io/api/v1/stock/profile2"
        const val getQuoteUrl = "https://finnhub.io/api/v1/quote"
        const val API_KEY = "cf9t7saad3ia9brrlmpgcf9t7saad3ia9brrlmq0"
    }
}