package com.antonpopoff.testproj.presentation.symboldetails

import android.os.Bundle
import android.view.View
import com.antonpopoff.testproj.R
import com.antonpopoff.testproj.presentation.common.BaseViewFragment
import com.antonpopoff.testproj.presentation.portfolio.models.Stock
import com.antonpopoff.testproj.utils.symbols.FormatUtils
import kotlinx.android.synthetic.main.fragment_portfolio.toolbar
import kotlinx.android.synthetic.main.fragment_symbol_details.*

class SymbolDetailsFragment : BaseViewFragment(R.layout.fragment_symbol_details) {

    private val symbol by lazy { arguments?.getParcelable<Stock>(SYMBOL) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        symbol?.let(::bindSymbol)
    }

    private fun setupToolbar() {
        toolbar.setNavigationIcon(R.drawable.ic_cross)
        toolbar.setNavigationOnClickListener { popFragment() }
    }

    private fun bindSymbol(symbol: Stock) {
        symbolTextView.text = symbol.name
        symbolPriceTextView.text = FormatUtils.formatSymbolPrice(symbol.latestHighPrice)
    }

    companion object {

        private const val SYMBOL = "symbol"

        fun create(symbol: Stock) = SymbolDetailsFragment().apply {
            arguments = Bundle().apply { putParcelable(SYMBOL, symbol) }
        }
    }
}
