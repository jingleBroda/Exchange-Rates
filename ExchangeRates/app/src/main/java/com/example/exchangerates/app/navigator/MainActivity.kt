package com.example.exchangerates.app.navigator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.exchangerates.R
import com.example.exchangerates.presentation.fragment.monetaryFragment.MonetaryRateMenuFragment
import java.io.Serializable

class MainActivity : AppCompatActivity(), Navigator {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_ExchangeRates)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null){
            showStartFragment()
        }
    }

    private fun launchFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentLayout, fragment)
            .addToBackStack(null)
            .show(fragment)
            .commit()
    }

    override fun showNewScreen(f: Fragment) {
        launchFragment(f)
    }

    override fun back() {
        onBackPressed()
    }

    override fun showStartFragment(){
        val f = MonetaryRateMenuFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentLayout, f)
            .show(f)
            .commit()
    }
}