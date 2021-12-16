package com.example.exchangerates.app.di.module

import com.example.exchangerates.presentation.fragment.monetaryFragment.MonetaryRateMenuFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun monetaryRateMenuFragment():MonetaryRateMenuFragment

}