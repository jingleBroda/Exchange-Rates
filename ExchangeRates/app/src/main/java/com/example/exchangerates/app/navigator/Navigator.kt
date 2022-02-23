package com.example.exchangerates.app.navigator

import androidx.fragment.app.Fragment

fun Fragment.navigator(): Navigator {
    return requireActivity() as Navigator
}

interface Navigator {
    fun showNewScreen(f:Fragment)
    fun back()
    fun showStartFragment()
}