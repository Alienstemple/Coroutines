package com.example.coroutines.presentation

import androidx.fragment.app.Fragment
import com.example.coroutines.domain.TickerInteractor
import com.example.coroutines.models.domain.TickerOutput

fun Fragment.navigator(): Navigator {
    return requireActivity() as Navigator
}

interface Navigator {
    fun showTickerDetails(tickerOutput: TickerOutput)
    fun hideTickerDetails()
    fun getTickerInteractor(): TickerInteractor
}