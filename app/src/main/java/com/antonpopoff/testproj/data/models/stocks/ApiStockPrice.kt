package com.antonpopoff.testproj.data.models.stocks

import com.squareup.moshi.Json

data class ApiStockPrice(
    @Json(name = "1. open") val open: Double,
    @Json(name = "2. high") val high: Double,
    @Json(name = "3. low") val low: Double,
    @Json(name = "4. close") val close: Double,
    @Json(name = "5. volume") val volume: Double
)
