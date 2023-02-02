package com.example.coroutines.models.data

import com.fasterxml.jackson.annotation.JsonProperty

data class TickerData(
    @JsonProperty("country") val country: String,
    @JsonProperty("currency") val currency: String,
    @JsonProperty("exchange") val exchange: String,
    @JsonProperty("finnhubIndustry") val finnhubIndustry: String,
    @JsonProperty("ipo") val ipo: String,
    @JsonProperty("logo") val logo: String,
    @JsonProperty("marketCapitalization") val marketCapitalization: Double,
    @JsonProperty("name") val name: String,
    @JsonProperty("phone") val phone: String,
    @JsonProperty("shareOutstanding") val shareOutstanding: Double,
    @JsonProperty("ticker") val ticker: String,
    @JsonProperty("weburl") val weburl: String
)
