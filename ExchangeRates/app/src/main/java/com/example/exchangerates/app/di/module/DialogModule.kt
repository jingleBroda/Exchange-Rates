package com.example.exchangerates.app.di.module

import com.example.exchangerates.presentation.dialog.calculateBottomFragment.CalculateBottomSheetFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DialogModule {
    @ContributesAndroidInjector
    abstract fun calculateBottomSheetFragment(): CalculateBottomSheetFragment
}