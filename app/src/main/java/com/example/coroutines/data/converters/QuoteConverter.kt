package com.example.coroutines.data.converters

import com.example.coroutines.models.data.QuoteData
import com.example.coroutines.models.domain.Quote

class QuoteConverter {
    fun convert(quoteData: QuoteData?): Quote? {
        return Quote(
            quoteData?.c,
            quoteData?.d,
            quoteData?.dp,
            quoteData?.h,
            quoteData?.l,
            quoteData?.o,
            quoteData?.pc,
            quoteData?.t)
    }
}