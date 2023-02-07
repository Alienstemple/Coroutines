package com.example.coroutines.data

import com.example.coroutines.models.data.QuoteData
import com.example.coroutines.models.data.TickerData
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Интерфейс, продоставляющий возможность получения по сети информации о компании ([getTickers])
 * и стоимостии ее акций ([getQuotes]) с помощью библиотеки Retrofit
 */
interface RetrofitService {

    /**
     * Get-запрос для получения информации о компании (TickerData)
     * @param symbol Имя тикера
     * @param token Регистрационный токен
     * @return Информации о компании (TickerData)
     */
    /* https://finnhub.io/dashboard */
    @GET("stock/profile2")
    suspend fun getTickers(@Query("symbol") symbol: String, @Query("token") token: String): Response<TickerData>

    /**
     * Get-запрос для получения стоимости акций компании (QuoteData)
     * @param symbol Имя тикера
     * @param token Регистрационный токен
     * @return Стоимость акций компании (QuoteData)
     */
    /* https://finnhub.io/dashboard */
    @GET("quote")
    suspend fun getQuotes(@Query("symbol") symbol: String, @Query("token") token: String): Response<QuoteData>

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
