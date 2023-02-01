package com.example.coroutines.data

import android.util.Log
import com.example.coroutines.domain.TickerRepository
import com.example.coroutines.models.Quote
import com.example.coroutines.models.Ticker

class TickerRepositoryImpl : TickerRepository {

    override suspend fun getTickerAndQuote(): Pair<Ticker, Quote> {

        // TODO async network call
        return Ticker("one", "one", "one", "one", "one",
            "one", 1.0, "one", "one", 1.0,
        "one", "one") to Quote(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0)
    }

    companion object {
        const val TAG = "InteractLog"
    }
}