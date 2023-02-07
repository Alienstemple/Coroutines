package com.example.coroutines.presentation.vm

import android.content.Context
import android.util.Log
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
import kotlinx.coroutines.flow.collect

/**
 * ViewModel для хранения списка тикеров и обновления информации о динамике цены
 * @constructor [tickerInteractor] Экземпляр интерактора, реализующий бизнес-логику
 */
class TickersViewModel(private val tickerInteractor: TickerInteractor) :
    ViewModel() {
    private val _tickerList = MutableLiveData<List<TickerOutput>>()

    /**
     * Список тикеров, хранит динамически обновляемые значения с помощью LiveData
     */
    val tickerList: LiveData<List<TickerOutput>> = _tickerList

    // 1 -- объявим state flow
    private val _tickerFlow = MutableStateFlow<List<TickerOutput>>(emptyList())
    val tickerFlow = _tickerFlow.asStateFlow()

    /**
     * Метод, асинхронно вызывающий интерактор для получения информации о тикерах из сети
     * @param context Контекст MainActivity. Понадобится для чтения входного списка компаний из файла
     * Корутина верхнего уровня запускается в [viewModelScope], обработчик исключений [handler]
     */
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

    fun getTickersAndQuotesAsFlow(context: Context) {
        val handler: CoroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
            println("Exception thrown in one of the children. $exception")
            Toast.makeText(context,
                "Exception thrown in one of the children. $exception",
                Toast.LENGTH_SHORT).show()
        }
        viewModelScope.launch(SupervisorJob() + Dispatchers.IO + handler) {
            val resultListFlow = tickerInteractor.getTickersAndQuotesAsFlow(context)
            resultListFlow.collect {
                Log.d(TAG, "Result list = $it")
            }

//            _tickerList.postValue(resultList)
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
