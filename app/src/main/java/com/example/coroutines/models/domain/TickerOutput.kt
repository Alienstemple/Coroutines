package com.example.coroutines.models.domain

data class TickerOutput(
    val logo: String? = "nan",
    val name: String? = "nan",
    val symbol: String? = "nan",
    val c: Double? = 0.0,
    val d: Double? = 0.0,
    val dp: Double? = 0.0
)
