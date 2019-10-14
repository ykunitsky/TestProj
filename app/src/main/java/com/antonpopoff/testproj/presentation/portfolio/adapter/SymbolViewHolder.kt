package com.antonpopoff.testproj.presentation.portfolio.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.antonpopoff.testproj.R
import com.antonpopoff.testproj.presentation.portfolio.models.Stock
import com.antonpopoff.testproj.utils.listeners.ItemClickListener
import com.antonpopoff.testproj.utils.symbols.FormatUtils

class SymbolViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val symbolsTextView = itemView.findViewById<TextView>(R.id.symbolTextView)
    private val symbolPriceTextView = itemView.findViewById<TextView>(R.id.symbolPriceTextView)

    var clickListener: ItemClickListener? = null

    init {
        itemView.setOnClickListener { clickListener?.invoke(adapterPosition) }
    }

    fun bind(stock: Stock) {
        symbolsTextView.text = stock.name
        symbolPriceTextView.text = FormatUtils.formatSymbolPrice(stock.latestHighPrice)
    }
}
