package com.antonpopoff.testproj.data.models

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json

data class ApiStock(
    @Json(name = "symbol") val symbol: String,
    @Json(name = "price") val price: Double
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString().orEmpty(),
        parcel.readDouble()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(symbol)
        parcel.writeDouble(price)
    }

    override fun describeContents() = 0

    companion object {

        @JvmField
        val CREATOR = object : Parcelable.Creator<ApiStock> {

            override fun createFromParcel(parcel: Parcel) = ApiStock(parcel)

            override fun newArray(size: Int) = arrayOfNulls<ApiStock>(size)
        }
    }
}
