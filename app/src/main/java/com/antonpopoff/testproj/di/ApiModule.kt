package com.antonpopoff.testproj.di

import com.antonpopoff.testproj.data.api.StocksApi
import com.antonpopoff.testproj.retrofit.RetrofitUtils
import com.antonpopoff.testproj.retrofit.create
import org.koin.core.scope.Scope
import org.koin.dsl.module
import retrofit2.Retrofit

private inline fun <reified T> Scope.createApi() = get<Retrofit>().create<T>()

val apiModule = module {
    single { RetrofitUtils.createRetrofitClient() }
    factory { createApi<StocksApi>() }
}
