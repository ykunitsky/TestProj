package com.antonpopoff.testproj.presentation.portfolio.models

import android.os.Parcel
import android.os.Parcelable

data class Stock(
    val name: String,
    val prices: List<StockPrice>
) : Parcelable {

    val latestHighPrice = prices.first().high

    constructor(parcel: Parcel) : this(
        parcel.readString().orEmpty(),
        parcel.createTypedArrayList(StockPrice.CREATOR).orEmpty()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeTypedList(prices)
    }

    override fun describeContents() = 0

    companion object {

        @JvmField
        val CREATOR = object : Parcelable.Creator<Stock> {

            override fun createFromParcel(parcel: Parcel) = Stock(parcel)

            override fun newArray(size: Int) = arrayOfNulls<Stock>(size)
        }
    }
}