package com.antonpopoff.testproj.presentation.portfolio.adapter

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.antonpopoff.testproj.R
import com.antonpopoff.testproj.presentation.portfolio.models.Stock
import com.antonpopoff.testproj.utils.listeners.ItemClickListener
import com.antonpopoff.testproj.utils.symbols.FormatUtils
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

class SymbolViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val symbolsTextView = itemView.findViewById<TextView>(R.id.symbolTextView)
    private val symbolPriceTextView = itemView.findViewById<TextView>(R.id.symbolPriceTextView)
    private val chartView = itemView.findViewById<LineChart>(R.id.chartView)

    var clickListener: ItemClickListener? = null

    init {
        itemView.setOnClickListener { clickListener?.invoke(adapterPosition) }
    }

    fun bind(stock: Stock) {
        val entries = stock.prices.map { Entry(it.date.time.toFloat(), it.high.toFloat()) }

        val dataSet = LineDataSet(entries, null).apply {
            color = Color.BLACK
            setDrawCircles(false)
        }

        chartView.setTouchEnabled(false)
        chartView.axisRight.isEnabled = false
        chartView.axisLeft.isEnabled = false
        chartView.xAxis.isEnabled = false
        chartView.legend.isEnabled = false
        chartView.description.isEnabled = false
        chartView.data = LineData(dataSet).apply { setDrawValues(false) }
        symbolsTextView.text = stock.name
        symbolPriceTextView.text = FormatUtils.formatSymbolPrice(stock.latestHighPrice)
    }
}
