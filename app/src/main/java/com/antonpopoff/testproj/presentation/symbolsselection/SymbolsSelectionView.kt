package com.antonpopoff.testproj.presentation.symbolsselection

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

interface SymbolsSelectionView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showEmptySymbolsStringError()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showPortfolio(symbols: List<String>)
}
