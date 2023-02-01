package com.example.coroutines.domain

import android.content.Context
import android.util.Log
import com.example.coroutines.models.TickerOutput
import com.example.coroutines.presentation.JsonToInputTickersConverter
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class TickerInteractorImpl(private val tickerRepository: TickerRepository) : TickerInteractor {

    override suspend fun getTickersAndQuotes(context: Context): List<TickerOutput> = coroutineScope {
        Log.d(TAG, "Test interact method called")
        val inputList = JsonToInputTickersConverter.getInputTickers(context)

        // TODO repo.getTickers(inputList), repo.getQuotes(inputList), merge
        inputList.take(30).map {
            async { tickerRepository.testRepo() }
        }.awaitAll()

        // return from coroutineScope
        listOf(TickerOutput(" ", "comp1", 0.0, 0.0, 0.0),
            TickerOutput(" ", "comp2", 0.0, 0.0, 0.0))
    }

    companion object {
        const val TAG = "InteractLog"
    }
}