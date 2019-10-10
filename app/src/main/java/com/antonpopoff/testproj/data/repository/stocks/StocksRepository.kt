package com.antonpopoff.testproj.data.repository.stocks

import com.antonpopoff.testproj.data.models.ApiStock
import io.reactivex.Single

interface StocksRepository {

    fun getStocks(symbols: List<String>): Single<List<ApiStock>>
}
