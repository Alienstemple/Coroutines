package com.example.coroutines.models.data

import com.fasterxml.jackson.annotation.JsonProperty

data class TickerQueryData (
    @JsonProperty("Name") val Name: String,
    @JsonProperty("Sector") val Sector: String,
    @JsonProperty("Symbol") val Symbol: String
)