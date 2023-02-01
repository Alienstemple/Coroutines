package com.example.coroutines.presentation.vm

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutines.domain.TickerInteractor
import com.example.coroutines.models.TickerOutput
import kotlinx.coroutines.*

class TickersViewModel(private val tickerInteractor: TickerInteractor) :
    ViewModel() {
    private val _tickerList = MutableLiveData<List<TickerOutput>>()
    val tickerList: LiveData<List<TickerOutput>> = _tickerList

    fun getTickersAndQuotes(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            val resultList = tickerInteractor.getTickersAndQuotes(context)
            _tickerList.postValue(resultList)
        }
    }

    companion object {
        const val TAG = "VMLog"
    }
}
