package com.example.coroutines.domain

import android.util.Log

class TickerInteractorImpl(val tickerRepository: TickerRepository): TickerInteractor {

    override fun testInteractorMethod() {
        Log.d(TAG, "Test interact method called")
        tickerRepository.testRepo()
    }

    companion object {
        const val TAG = "InteractLog"
    }
}