package com.antonpopoff.testproj.presentation.portfolio

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.antonpopoff.testproj.R
import com.antonpopoff.testproj.data.models.ApiStock
import com.antonpopoff.testproj.presentation.common.BaseViewFragment
import com.antonpopoff.testproj.presentation.portfolio.adapter.SymbolsAdapter
import com.antonpopoff.testproj.presentation.symboldetails.SymbolDetailsFragment
import com.antonpopoff.testproj.utils.viewstate.ViewState
import com.antonpopoff.testproj.utils.viewstate.ViewStateUpdater
import kotlinx.android.synthetic.main.fragment_portfolio.*
import moxy.ktx.moxyPresenter

class PortfolioFragment : BaseViewFragment(R.layout.fragment_portfolio), PortfolioView {

    private lateinit var viewStateUpdater: ViewStateUpdater

    private val presenter by moxyPresenter { createPresenter() }

    private fun createPresenter() = PortfolioPresenter(arguments?.getStringArray(SYMBOLS)?.toList().orEmpty())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewStateUpdater = ViewStateUpdater(symbolsRecycler, emptyView, loadingView, errorView)
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

    override fun bindSymbols(symbols: List<ApiStock>) {
        (symbolsRecycler.adapter as? SymbolsAdapter)?.also {
            it.symbols = symbols
            it.notifyDataSetChanged()
        }
    }

    override fun updateSymbolsViewState(state: ViewState) {
        viewStateUpdater.update(state)
    }

    override fun showSymbolDetails(symbol: ApiStock) {
        pushFragment(R.id.fragmentsContainer, SymbolDetailsFragment.create(symbol))
    }

    companion object {

        private const val SYMBOLS = "symbols"

        fun create(symbols: List<String>) = PortfolioFragment().apply {
            arguments = Bundle().apply { putStringArray(SYMBOLS, symbols.toTypedArray()) }
        }
    }
}
