package com.example.coroutines.presentation.vm

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutines.domain.TickerInteractor
import com.example.coroutines.models.domain.TickerOutput
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TickersViewModel(private val tickerInteractor: TickerInteractor) :
    ViewModel() {
    private val _tickerList = MutableLiveData<List<TickerOutput>>()
    val tickerList: LiveData<List<TickerOutput>> = _tickerList

    // 1 -- объявим state flow
    private val _tickerFlow = MutableStateFlow<List<TickerOutput>>(emptyList())
    val tickerFlow = _tickerFlow.asStateFlow()

    fun getTickersAndQuotes(context: Context) {
        val handler: CoroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
            println("Exception thrown in one of the children. $exception")
            Toast.makeText(context,
                "Exception thrown in one of the children. $exception",
                Toast.LENGTH_SHORT).show()
        }
        viewModelScope.launch(SupervisorJob() + Dispatchers.IO + handler) {
            val resultList = tickerInteractor.getTickersAndQuotes(context)
            _tickerList.postValue(resultList)
        }
    }

    // 2 -- отправим значение в state flow
    fun updateStateFlow() {
        _tickerFlow.value = listOf(
            TickerOutput(),
            TickerOutput(),
            TickerOutput()
        )
    }

    companion object {
        const val TAG = "VMLog"
    }
}
