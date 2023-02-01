package com.example.coroutines.domain

import android.util.Log

class TickerInteractorImpl: TickerInteractor {
    override fun testInteractorMethod() {
        Log.d(TAG, "Test interact method called")
        // TODO call Repository method
    }

    companion object {
        const val TAG = "InteractLog"
    }
}