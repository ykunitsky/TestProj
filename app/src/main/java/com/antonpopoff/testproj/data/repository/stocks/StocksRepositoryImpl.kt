package com.antonpopoff.testproj.data.repository.stocks

import com.antonpopoff.testproj.data.api.StocksApi
import com.antonpopoff.testproj.data.models.stocks.ApiStockPrice
import com.antonpopoff.testproj.presentation.portfolio.models.Stock
import com.antonpopoff.testproj.presentation.portfolio.models.StockPrice
import io.reactivex.Observable
import io.reactivex.Single
import java.text.SimpleDateFormat
import java.util.*

private const val PRICE_DATE_FORMAT = "yyyy-MM-dd"

private const val FUNCTION = "TIME_SERIES_DAILY"

private const val API_KEY = "Z3286Z4Q8G3Q0PRQ"

class StocksRepositoryImpl(private val stocksApi: StocksApi) : StocksRepository {

    override fun getStocks(symbols: List<String>, historicalPriceTicks: Int): Single<List<Stock>> = Observable
        .fromIterable(symbols)
        .flatMapSingle { requestStock(it, historicalPriceTicks) }
        .toList()

    private fun requestStock(symbol: String, historicalPriceTicks: Int) = stocksApi.getStock(symbol, FUNCTION, API_KEY)
        .map {
            Stock(it.metaData.symbol, mapPrices(it.prices, historicalPriceTicks))
        }

    private fun mapPrices(prices: Map<String, ApiStockPrice>, historicalPriceTicks: Int) = prices
        .map {
            val dateFormat = SimpleDateFormat(PRICE_DATE_FORMAT, Locale.getDefault())
            val date = dateFormat.parse(it.key)
            StockPrice(date, it.value.high, it.value.low)
        }
        .sortedBy { it.date }
        .take(historicalPriceTicks)
}
