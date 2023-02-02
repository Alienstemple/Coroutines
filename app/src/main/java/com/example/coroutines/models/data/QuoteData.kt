package com.example.coroutines.models.data

import com.fasterxml.jackson.annotation.JsonProperty

data class QuoteData(
    @JsonProperty("c") val c: Double,
    @JsonProperty("d") val d: Double,
    @JsonProperty("dp") val dp: Double,
    @JsonProperty("h") val h: Double,
    @JsonProperty("l") val l: Double,
    @JsonProperty("o") val o: Double,
    @JsonProperty("pc") val pc: Double,
    @JsonProperty("t") val t: Double
)
