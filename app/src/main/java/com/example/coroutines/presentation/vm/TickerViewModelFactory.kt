package com.example.coroutines.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.coroutines.domain.TickerInteractor

class TickerViewModelFactory(val tickerInteractor: TickerInteractor) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(TickerInteractor::class.java).newInstance(tickerInteractor)
    }
}