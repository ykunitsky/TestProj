package com.antonpopoff.testproj.presentation.portfolio

import com.antonpopoff.testproj.data.models.stocks.ApiStock
import com.antonpopoff.testproj.data.repository.stocks.StocksRepository
import com.antonpopoff.testproj.presentation.common.BasePresenter
import com.antonpopoff.testproj.presentation.portfolio.models.Stock
import com.antonpopoff.testproj.utils.viewstate.Data
import com.antonpopoff.testproj.utils.viewstate.Error
import com.antonpopoff.testproj.utils.viewstate.Loading
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import org.koin.core.inject

private const val HISTORICAL_PRICE_TICKS_NUM = 10

@InjectViewState
class PortfolioPresenter(private val symbols: List<String>) : BasePresenter<PortfolioView>() {

    private var stocks = emptyList<Stock>()

    private val stocksRepository by inject<StocksRepository>()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        requestStocksList()
    }

    private fun requestStocksList() {
        viewState.renderStocksModel(Loading())

        stocksRepository.getStocks(symbols, HISTORICAL_PRICE_TICKS_NUM)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::onStocksResponse, this::onStocksError)
            .disposeOnDestroy()
    }

    private fun onStocksResponse(stocks: List<Stock>) {
        this.stocks = stocks
        viewState.renderStocksModel(Data(stocks))
    }

    private fun onStocksError(e: Throwable) {
        viewState.renderStocksModel(Error(e))
    }

    fun onSymbolClick(position: Int) {
        viewState.showSymbolDetails(stocks[position])
    }
}
