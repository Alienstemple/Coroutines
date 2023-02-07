package com.example.coroutines.models.data

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Класс уровня data, описывающий динамику цены акции данной компании
 * @property c Текущая цена акции компании
 * @property d Изменение цены
 * @property dp Изменение цены в процентах
 * @property h Самая высокая цена за текущий день
 * @property l Самая низкая цена за текущий день
 * @property o Открытая цена
 * @property pc Предыдущая закрытая цена
 * @property t UNIX-timestamp
 */
/* https://finnhub.io/docs/api/quote */
data class QuoteData(
    @JsonProperty("c") val c: Double?,
    @JsonProperty("d") val d: Double?,
    @JsonProperty("dp") val dp: Double?,
    @JsonProperty("h") val h: Double?,
    @JsonProperty("l") val l: Double?,
    @JsonProperty("o") val o: Double?,
    @JsonProperty("pc") val pc: Double?,
    @JsonProperty("t") val t: Double?
)
