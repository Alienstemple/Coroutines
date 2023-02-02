package com.example.coroutines.presentation

import androidx.fragment.app.Fragment

fun Fragment.navigator(): Navigator {
    return requireActivity() as Navigator
}

interface Navigator {
    fun showTickerDetails()
    fun hideTickerDetails()
}