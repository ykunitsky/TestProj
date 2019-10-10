package com.antonpopoff.testproj.utils.softinput

import android.view.WindowManager

enum class SoftInputMethod(val value: Int) {
    ADJUST_RESIZE(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE),
    ADJUST_PAN(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
}
