package com.antonpopoff.testproj.presentation.symbolsselection

import android.os.Bundle
import android.view.View
import com.antonpopoff.testproj.R
import com.antonpopoff.testproj.presentation.common.BaseViewFragment
import com.antonpopoff.testproj.presentation.portfolio.PortfolioFragment
import kotlinx.android.synthetic.main.fragment_symbols_selection.*
import moxy.ktx.moxyPresenter

class SymbolsSelectionFragment : BaseViewFragment(R.layout.fragment_symbols_selection), SymbolsSelectionView {

    private val presenter by moxyPresenter { SymbolsSelectionPresenter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nextButton.setOnClickListener { onNextButtonClick() }
    }

    private fun onNextButtonClick() {
        val symbolsString = symbolsEditText.text?.toString().orEmpty()
        presenter.parseSymbolsString(symbolsString)
    }

    override fun showEmptySymbolsStringError() {
        symbolsTextInputLayout.error = getString(R.string.type_symbols)
    }

    override fun showPortfolio(symbols: List<String>) {
        pushFragment(R.id.fragmentsContainer, PortfolioFragment.create(symbols))
    }
}
