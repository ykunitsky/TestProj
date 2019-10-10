package com.antonpopoff.testproj.retrofit

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

private const val STOCK_API_URL = "https://financialmodelingprep.com/api/v3/stock/"

class RetrofitUtils private constructor() {

    companion object {

        fun createRetrofitClient(): Retrofit = Retrofit.Builder()
            .baseUrl(STOCK_API_URL)
            .addConverterFactory(createMoshiConverterFactory())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        private fun createMoshiConverterFactory() = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
            .let { MoshiConverterFactory.create(it) }
    }
}
