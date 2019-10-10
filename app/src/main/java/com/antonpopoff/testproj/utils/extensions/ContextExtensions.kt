package com.antonpopoff.testproj.utils.extensions

import android.content.Context
import android.inputmethodservice.InputMethodService
import android.view.inputmethod.InputMethodManager

val Context.inputMethodManager get() = this.getSystemService(InputMethodService.INPUT_METHOD_SERVICE) as InputMethodManager
