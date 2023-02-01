package com.example.coroutines.domain

import android.content.Context
import android.util.Log
import com.example.coroutines.models.TickerOutput
import com.example.coroutines.presentation.JsonToInputTickersConverter

class TickerInteractorImpl(private val tickerRepository: TickerRepository) : TickerInteractor {

    override fun getTickersAndQuotes(context: Context): List<TickerOutput> {
        Log.d(TAG, "Test interact method called")
        val inputList = JsonToInputTickersConverter.getInputTickers(context)
        // TODO repo.getTickers(inputList), repo.getQuotes(inputList), merge

        tickerRepository.testRepo()
        return listOf(TickerOutput(" ", "comp1", 0.0, 0.0, 0.0),
            TickerOutput(" ", "comp2", 0.0, 0.0, 0.0))
    }

    companion object {
        const val TAG = "InteractLog"
    }
}