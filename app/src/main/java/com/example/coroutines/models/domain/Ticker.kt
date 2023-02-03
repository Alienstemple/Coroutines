package com.example.coroutines.models.domain

data class Ticker(
    val country: String? = "",
    val currency: String? = "",
    val exchange: String? = "",
    val finnhubIndustry: String? = "",
    val ipo: String? = "",
    val logo: String? = "",
    val marketCapitalization: Double? = 0.0,
    val name: String? = "",
    val phone: String? = "",
    val shareOutstanding: Double? = 0.0,
    val ticker: String? = "",
    val weburl: String? = ""
)
