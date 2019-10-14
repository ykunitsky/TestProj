package com.antonpopoff.testproj.presentation.portfolio

import com.antonpopoff.testproj.data.models.stocks.ApiStock
import com.antonpopoff.testproj.presentation.portfolio.models.Stock
import com.antonpopoff.testproj.utils.viewstate.Model
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

interface PortfolioView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun renderStocksModel(model: Model<List<Stock>>)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showSymbolDetails(symbol: Stock)
}
