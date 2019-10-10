package com.antonpopoff.testproj.presentation.portfolio

import com.antonpopoff.testproj.data.models.ApiStock
import com.antonpopoff.testproj.utils.viewstate.ViewState
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

interface PortfolioView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun bindSymbols(symbols: List<ApiStock>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun updateSymbolsViewState(state: ViewState)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showSymbolDetails(symbol: ApiStock)
}
