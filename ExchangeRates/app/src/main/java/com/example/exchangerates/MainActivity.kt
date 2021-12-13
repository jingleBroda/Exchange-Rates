package com.example.exchangerates

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.exchangerates.navigator.Navigator
import com.example.exchangerates.presentation.fragment.monetaryFragment.MonetaryRateMenuFragment

class MainActivity : AppCompatActivity(), Navigator {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_ExchangeRates)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showStartFragment()


    }

    private fun showStartFragment(){
        val startFragment = MonetaryRateMenuFragment()
        showNewScreen(startFragment)
    }

    override fun showNewScreen(f: Fragment) {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentLayout, f)
            .show(f)
            .commit()
    }

}