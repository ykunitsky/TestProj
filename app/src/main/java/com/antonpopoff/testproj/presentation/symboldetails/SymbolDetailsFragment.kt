package com.antonpopoff.testproj.presentation.symboldetails

import android.os.Bundle
import android.view.View
import com.antonpopoff.testproj.R
import com.antonpopoff.testproj.data.models.ApiStock
import com.antonpopoff.testproj.presentation.common.BaseViewFragment
import com.antonpopoff.testproj.utils.symbols.FormatUtils
import kotlinx.android.synthetic.main.fragment_portfolio.*
import kotlinx.android.synthetic.main.view_holder_symbol.*

class SymbolDetailsFragment : BaseViewFragment(R.layout.fragment_symbol_details) {

    private val symbol by lazy { arguments?.getParcelable<ApiStock>(SYMBOL) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        symbol?.let(::bindSymbol)
    }

    private fun setupToolbar() {
        toolbar.setNavigationIcon(R.drawable.ic_cross)
        toolbar.setNavigationOnClickListener { popFragment() }
    }

    private fun bindSymbol(symbol: ApiStock) {
        symbolTextView.text = symbol.symbol
        symbolPriceTextView.text = FormatUtils.formatSymbolPrice(symbol.price)
    }

    companion object {

        private const val SYMBOL = "symbol"

        fun create(symbol: ApiStock) = SymbolDetailsFragment().apply {
            arguments = Bundle().apply { putParcelable(SYMBOL, symbol) }
        }
    }
}
