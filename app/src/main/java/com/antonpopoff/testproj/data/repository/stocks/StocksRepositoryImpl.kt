package com.antonpopoff.testproj.data.repository.stocks

import com.antonpopoff.testproj.data.api.StocksApi
import com.antonpopoff.testproj.data.models.ApiStock
import com.antonpopoff.testproj.data.models.ApiStocksList
import io.reactivex.Single

class StocksRepositoryImpl(private val stocksApi: StocksApi) : StocksRepository {

    override fun getStocks(symbols: List<String>): Single<List<ApiStock>> = symbols.joinToString(",").let {
        if (symbols.size > 1) {
            stocksApi.getStocksList(it).map(ApiStocksList::stocks)
        } else {
            stocksApi.getStock(it).map(::listOf)
        }
    }
}
