package com.example.coroutines.data

import com.example.coroutines.models.data.QuoteData
import com.example.coroutines.models.data.TickerData
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("stock/profile2")
    suspend fun getTickers(@Query("symbol") user: String, @Query("token") token: String): Response<TickerData>

    @GET("quote")
    suspend fun getQuotes(@Query("symbol") user: String, @Query("token") token: String): Response<QuoteData>

    companion object {
        var retrofitService: RetrofitService? = null
        fun getInstance(): RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://finnhub.io/api/v1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}
