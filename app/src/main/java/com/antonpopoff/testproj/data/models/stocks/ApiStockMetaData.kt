package com.antonpopoff.testproj.data.models.stocks

import com.squareup.moshi.Json

data class ApiStockMetaData(@Json(name = "2. Symbol") val symbol: String)
