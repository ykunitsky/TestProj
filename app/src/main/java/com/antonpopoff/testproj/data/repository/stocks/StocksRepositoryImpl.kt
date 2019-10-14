package com.antonpopoff.testproj.data.repository.stocks

import com.antonpopoff.testproj.data.api.StocksApi
import com.antonpopoff.testproj.data.models.stocks.ApiStockPrice
import com.antonpopoff.testproj.data.models.stocks.toUIModel
import com.antonpopoff.testproj.presentation.portfolio.models.Stock
import io.reactivex.Observable
import io.reactivex.Single

class StocksRepositoryImpl(private val stocksApi: StocksApi) : StocksRepository {

    override fun getStocks(symbols: List<String>, historicalPriceTicks: Int): Single<List<Stock>> = Observable
        .fromIterable(symbols)
        .flatMapSingle { requestStock(it, historicalPriceTicks) }
        .toList()

    private fun requestStock(symbol: String, historicalPriceTicks: Int) = stocksApi.getStock(symbol)
        .map { it.toUIModel(mapPrices(it.prices, historicalPriceTicks)) }

    private fun mapPrices(prices: Map<String, ApiStockPrice>, historicalPriceTicks: Int) = prices
        .map { it.value.toUIModel(it.key) }
        .sortedByDescending { it.date }
        .take(historicalPriceTicks)
}
