package com.antonpopoff.testproj.presentation.common

import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.fragment.app.FragmentTransaction
import com.antonpopoff.testproj.utils.softinput.SoftInputMethod
import moxy.MvpAppCompatFragment

open class BaseViewFragment : MvpAppCompatFragment {

    open val preferredSoftInputMethod = SoftInputMethod.ADJUST_PAN

    constructor()

    constructor(@LayoutRes layoutId: Int) : super(layoutId)

    override fun onResume() {
        super.onResume()
        setupPreferredSoftInputMethod()
    }

    private fun setupPreferredSoftInputMethod() {
        activity?.window?.setSoftInputMode(preferredSoftInputMethod.value)
    }

    protected fun pushFragment(@IdRes id: Int, fragment: BaseViewFragment) {
        fragmentManager?.beginTransaction()
            ?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            ?.replace(id, fragment)
            ?.addToBackStack(null)
            ?.commit()
    }

    protected fun popFragment() {
        fragmentManager?.popBackStack()
    }
}
