package com.antonpopoff.testproj.presentation.portfolio

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.antonpopoff.testproj.R
import com.antonpopoff.testproj.data.models.stocks.ApiStock
import com.antonpopoff.testproj.presentation.common.BaseViewFragment
import com.antonpopoff.testproj.presentation.portfolio.adapter.SymbolsAdapter
import com.antonpopoff.testproj.presentation.portfolio.models.Stock
import com.antonpopoff.testproj.presentation.symboldetails.SymbolDetailsFragment
import com.antonpopoff.testproj.utils.extensions.android.goneUnless
import com.antonpopoff.testproj.utils.viewstate.*
import kotlinx.android.synthetic.main.fragment_portfolio.*
import moxy.ktx.moxyPresenter

class PortfolioFragment : BaseViewFragment(R.layout.fragment_portfolio), PortfolioView {

    private val presenter by moxyPresenter { createPresenter() }

    private fun createPresenter() = PortfolioPresenter(arguments?.getStringArray(SYMBOLS)?.toList().orEmpty())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        setupSymbolsRecycler()
    }

    private fun setupToolbar() {
        toolbar.setNavigationIcon(R.drawable.ic_arrow_left)
        toolbar.setNavigationOnClickListener { popFragment() }
    }

    private fun setupSymbolsRecycler() {
        symbolsRecycler.layoutManager = LinearLayoutManager(requireContext())
        symbolsRecycler.adapter = SymbolsAdapter().apply { clickListener = presenter::onSymbolClick }
    }

    override fun renderStocksModel(model: Model<List<Stock>>) {
        if (model is Data<List<Stock>>) showStocksList(model.data)
        updateViewState(model)
    }

    private fun showStocksList(symbols: List<Stock>) {
        (symbolsRecycler.adapter as? SymbolsAdapter)?.also {
            it.symbols = symbols
            it.notifyDataSetChanged()
        }
    }

    private fun updateViewState(model: Model<*>) {
        symbolsRecycler?.goneUnless(model is Data)
        emptyView?.goneUnless(model is Empty)
        loadingView?.goneUnless(model is Loading)
        errorView?.goneUnless(model is Error)
    }

    override fun showSymbolDetails(symbol: Stock) {
        pushFragment(R.id.fragmentsContainer, SymbolDetailsFragment.create(symbol))
    }

    companion object {

        private const val SYMBOLS = "symbols"

        fun create(symbols: List<String>) = PortfolioFragment().apply {
            arguments = Bundle().apply { putStringArray(SYMBOLS, symbols.toTypedArray()) }
        }
    }
}
