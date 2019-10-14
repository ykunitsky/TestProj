package com.antonpopoff.testproj.data.models.stocks

import com.antonpopoff.testproj.presentation.portfolio.models.StockPrice
import com.squareup.moshi.Json
import java.text.SimpleDateFormat
import java.util.*

private const val PRICE_DATE_FORMAT = "yyyy-MM-dd"

data class ApiStockPrice(
    @Json(name = "1. open") val open: Double,
    @Json(name = "2. high") val high: Double,
    @Json(name = "3. low") val low: Double,
    @Json(name = "4. close") val close: Double,
    @Json(name = "5. volume") val volume: Double
)

fun ApiStockPrice.toUIModel(date: String) = StockPrice(parsePriceDate(date), this.high, this.low)

private fun parsePriceDate(date: String) = SimpleDateFormat(PRICE_DATE_FORMAT, Locale.getDefault()).run { parse(date) }
