package com.antonpopoff.testproj.data.models

import com.squareup.moshi.Json

data class ApiStocksList(@Json(name = "companiesPriceList") val stocks: List<ApiStock>)
