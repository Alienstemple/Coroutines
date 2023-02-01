package com.example.coroutines.data

import android.util.Log
import com.example.coroutines.domain.TickerRepository

class TickerRepositoryImpl : TickerRepository {
    override fun testRepo() {
        Log.d(TAG, "Test repository method called")
    }

    companion object {
        const val TAG = "InteractLog"
    }
}