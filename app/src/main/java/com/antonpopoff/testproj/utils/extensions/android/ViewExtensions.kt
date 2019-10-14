package com.antonpopoff.testproj.utils.extensions.android

import android.view.View

fun View.goneUnless(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}
