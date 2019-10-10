package com.antonpopoff.testproj.presentation.portfolio.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.antonpopoff.testproj.R
import com.antonpopoff.testproj.data.models.ApiStock
import com.antonpopoff.testproj.utils.listeners.ItemClickListener

class SymbolsAdapter(var symbols: List<ApiStock> = emptyList()) : RecyclerView.Adapter<SymbolViewHolder>() {

    var clickListener: ItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SymbolViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.view_holder_symbol, parent, false)
        return SymbolViewHolder(itemView).also { it.clickListener = clickListener }
    }

    override fun onBindViewHolder(holder: SymbolViewHolder, position: Int) {
        holder.bind(symbols[position])
    }

    override fun getItemCount() = symbols.size
}
