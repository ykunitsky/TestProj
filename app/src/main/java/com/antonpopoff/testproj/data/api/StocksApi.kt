package com.antonpopoff.testproj.data.api

import com.antonpopoff.testproj.data.models.stocks.ApiStock
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface StocksApi {

    @GET("query?function=TIME_SERIES_DAILY&apikey=Z3286Z4Q8G3Q0PRQ")
    fun getStock(@Query("symbol") symbols: String): Single<ApiStock>
}
