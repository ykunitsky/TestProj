package com.antonpopoff.testproj.utils.extensions

import android.view.View
import androidx.fragment.app.Fragment

val Fragment.inputMethodManager get() = this.context?.inputMethodManager
