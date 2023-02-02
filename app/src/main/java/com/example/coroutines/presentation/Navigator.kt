package com.example.coroutines.presentation

import androidx.fragment.app.Fragment
import com.example.coroutines.domain.TickerInteractor

fun Fragment.navigator(): Navigator {
    return requireActivity() as Navigator
}

interface Navigator {
    fun showTickerDetails()
    fun hideTickerDetails()
    fun getTickerInteractor(): TickerInteractor
}