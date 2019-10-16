package com.antonpopoff.testproj.data.api

import com.antonpopoff.testproj.data.models.stocks.ApiStock
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface StocksApi {

    @GET("query")
    fun getStock(
        @Query("symbol") symbols: String,
        @Query("function") function: String,
        @Query("apikey") apiKey: String): Single<ApiStock>
}
