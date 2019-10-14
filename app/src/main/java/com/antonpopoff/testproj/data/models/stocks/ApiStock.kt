package com.antonpopoff.testproj.data.models.stocks

import com.antonpopoff.testproj.presentation.portfolio.models.Stock
import com.antonpopoff.testproj.presentation.portfolio.models.StockPrice
import com.squareup.moshi.Json

data class ApiStock(
    @Json(name = "Meta Data") val metaData: ApiStockMetaData,
    @Json(name = "Time Series (Daily)") val prices: Map<String, ApiStockPrice>
)

fun ApiStock.toUIModel(prices: List<StockPrice>) = Stock(this.metaData.symbol, prices)
