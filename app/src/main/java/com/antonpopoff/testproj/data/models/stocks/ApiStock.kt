package com.antonpopoff.testproj.data.models.stocks

import com.squareup.moshi.Json

data class ApiStock(
    @Json(name = "Meta Data") val metaData: ApiStockMetaData,
    @Json(name = "Time Series (Daily)") val prices: Map<String, ApiStockPrice>
)
