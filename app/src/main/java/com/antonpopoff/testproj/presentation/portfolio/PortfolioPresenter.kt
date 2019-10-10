package com.antonpopoff.testproj.presentation.portfolio

import com.antonpopoff.testproj.data.models.ApiStock
import com.antonpopoff.testproj.data.repository.stocks.StocksRepository
import com.antonpopoff.testproj.presentation.common.BasePresenter
import com.antonpopoff.testproj.utils.viewstate.ViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import org.koin.core.inject
import retrofit2.HttpException
import java.net.UnknownHostException

@InjectViewState
class PortfolioPresenter(private val symbols: List<String>) : BasePresenter<PortfolioView>() {

    private var stocks = emptyList<ApiStock>()

    private val stocksRepository by inject<StocksRepository>()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        requestStocksList()
    }

    private fun requestStocksList() {
        viewState.updateSymbolsViewState(ViewState.LOADING)

        stocksRepository.getStocks(symbols)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::onStocksResponse, this::onStocksError)
            .disposeOnDestroy()
    }

    private fun onStocksResponse(stocks: List<ApiStock>) {
        this.stocks = stocks

        if (stocks.isNotEmpty()) {
            viewState.bindSymbols(stocks)
            viewState.updateSymbolsViewState(ViewState.CONTENT)
        } else {
            viewState.updateSymbolsViewState(ViewState.EMPTY)
        }
    }

    private fun onStocksError(e: Throwable) {
        if (e is HttpException || e is UnknownHostException) {
            viewState.updateSymbolsViewState(ViewState.ERROR)
        } else {
            viewState.updateSymbolsViewState(ViewState.EMPTY)
        }
    }

    fun onSymbolClick(position: Int) {
        viewState.showSymbolDetails(stocks[position])
    }
}
