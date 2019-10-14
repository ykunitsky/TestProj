package com.antonpopoff.testproj.data.repository.stocks

import com.antonpopoff.testproj.presentation.portfolio.models.Stock
import io.reactivex.Single

interface StocksRepository {

    fun getStocks(symbols: List<String>, historicalPriceTicks: Int): Single<List<Stock>>
}
