package com.antonpopoff.testproj.utils.viewstate

import android.view.View
import com.antonpopoff.testproj.utils.extensions.goneUnless

class ViewStateUpdater(
    private val content: View? = null,
    private val empty: View? = null,
    private val loading: View? = null,
    private val error: View? = null
) {

    fun update(state: ViewState) {
        content?.goneUnless(state === ViewState.CONTENT)
        empty?.goneUnless(state === ViewState.EMPTY)
        loading?.goneUnless(state === ViewState.LOADING)
        error?.goneUnless(state === ViewState.ERROR)
    }
}
