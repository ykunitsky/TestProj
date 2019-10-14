package com.antonpopoff.testproj.retrofit

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

private const val STOCK_API_URL = "https://www.alphavantage.co/"

class RetrofitUtils private constructor() {

    companion object {

        fun createRetrofitClient(): Retrofit = Retrofit.Builder()
            .baseUrl(STOCK_API_URL)
            .client(createOkHttpClient())
            .addConverterFactory(createMoshiConverterFactory())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        private fun createOkHttpClient() = OkHttpClient.Builder()
            .addInterceptor(createHttpLogger())
            .build()

        private fun createHttpLogger() = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        private fun createMoshiConverterFactory() = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
            .let { MoshiConverterFactory.create(it) }
    }
}
