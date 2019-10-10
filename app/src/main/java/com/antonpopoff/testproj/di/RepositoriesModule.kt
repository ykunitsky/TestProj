package com.antonpopoff.testproj.di

import com.antonpopoff.testproj.data.repository.stocks.StocksRepository
import com.antonpopoff.testproj.data.repository.stocks.StocksRepositoryImpl
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    factory { StocksRepositoryImpl(get()) } bind StocksRepository::class
}
