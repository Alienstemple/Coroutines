package com.example.coroutines.presentation.vm

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutines.domain.TickerInteractor
import com.example.coroutines.models.domain.Ticker
import com.example.coroutines.models.domain.TickerOutput
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class TickerDetailsViewModel(private val tickerInteractor: TickerInteractor): ViewModel() {
    private val _ticker = MutableLiveData<Ticker>()
    val ticker: LiveData<Ticker> = _ticker

    fun getTickerDetails(tickerOutput: TickerOutput) {
        val handler: CoroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
            println("Exception thrown in one of the children. $exception")
        }
        viewModelScope.launch(SupervisorJob() + Dispatchers.IO + handler) {
            val result = tickerInteractor.getTickerDetails(tickerOutput)
            _ticker.postValue(result)
        }
    }

    companion object {
        const val TAG = "VMLog"
    }
}