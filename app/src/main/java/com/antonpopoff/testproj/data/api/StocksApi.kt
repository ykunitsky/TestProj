package com.antonpopoff.testproj.data.api

import com.antonpopoff.testproj.data.models.ApiStock
import com.antonpopoff.testproj.data.models.ApiStocksList
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface StocksApi {

    @GET("real-time-price/{searchString}")
    fun getStock(@Path("searchString") symbols: String): Single<ApiStock>

    @GET("real-time-price/{searchString}")
    fun getStocksList(@Path("searchString") symbols: String): Single<ApiStocksList>
}
