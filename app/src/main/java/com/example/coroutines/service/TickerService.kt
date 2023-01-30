package com.example.coroutines.service

import com.example.coroutines.data.Quote
import com.example.coroutines.data.Ticker
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface TickerService {

    @GET("stock/profile2")
    suspend fun getTickers(@Query("symbol") user: String, @Query("token") token: String): Response<Ticker>

    @GET("quote")
    suspend fun getQuotes(@Query("symbol") user: String, @Query("token") token: String): Response<Quote>

    companion object {
        var retrofitService: TickerService? = null
        fun getInstance(): TickerService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://finnhub.io/api/v1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(TickerService::class.java)
            }
            return retrofitService!!
        }
    }
}
