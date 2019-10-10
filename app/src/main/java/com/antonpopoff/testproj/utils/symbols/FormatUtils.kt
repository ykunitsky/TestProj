package com.antonpopoff.testproj.utils.symbols

import java.util.*

class FormatUtils private constructor(){

    companion object {

        fun formatSymbolPrice(price: Double) = String.format(Locale.getDefault(), "%.2f", price)
    }
}
