package com.antonpopoff.testproj.presentation.portfolio.models

import android.os.Parcel
import android.os.Parcelable
import com.antonpopoff.testproj.utils.extensions.android.readDate
import com.antonpopoff.testproj.utils.extensions.android.writeDate
import java.util.*

data class StockPrice(
    val date: Date,
    val high: Double,
    val low: Double
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readDate(),
        parcel.readDouble(),
        parcel.readDouble()
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeDate(date)
        dest.writeDouble(high)
        dest.writeDouble(low)
    }

    override fun describeContents() = 0

    companion object {

        @JvmField
        val CREATOR = object : Parcelable.Creator<StockPrice> {

            override fun createFromParcel(parcel: Parcel) = StockPrice(parcel)

            override fun newArray(size: Int) = arrayOfNulls<StockPrice>(size)
        }
    }
}
