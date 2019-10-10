package com.antonpopoff.testproj.presentation.symbolsselection

import com.antonpopoff.testproj.presentation.common.BasePresenter
import moxy.InjectViewState

private const val SYMBOLS_SEPARATOR = ","

private const val SYMBOLS_MAX_AMOUNT = 5

@InjectViewState
class SymbolsSelectionPresenter : BasePresenter<SymbolsSelectionView>() {

    fun parseSymbolsString(symbolsStr: String) {
        val symbols = parseSymbols(symbolsStr)

        if (symbols.isNotEmpty()) {
            viewState.showPortfolio(symbols)
        } else {
            viewState.showEmptySymbolsStringError()
        }
    }

    private fun parseSymbols(symbolsStr: String) = symbolsStr
        .split(SYMBOLS_SEPARATOR)
        .map(String::trim)
        .filter(String::isNotBlank)
        .take(SYMBOLS_MAX_AMOUNT)
}
