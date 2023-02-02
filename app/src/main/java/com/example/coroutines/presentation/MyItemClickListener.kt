package com.example.coroutines.presentation

import com.example.coroutines.models.domain.TickerOutput

interface MyItemClickListener {
    fun onItemClicked(tickerOutput: TickerOutput)
}