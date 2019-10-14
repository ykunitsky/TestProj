package com.antonpopoff.testproj.utils.extensions.android

import android.os.Parcel
import java.util.*

fun Parcel.writeDate(date: Date) {
    this.writeSerializable(date)
}

fun Parcel.readDate() = this.readSerializable() as Date
