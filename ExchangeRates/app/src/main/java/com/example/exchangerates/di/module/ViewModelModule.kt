package com.example.exchangerates.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.exchangerates.di.viewModelPropertyInject.ViewModelFactory
import com.example.exchangerates.di.viewModelPropertyInject.ViewModelKey
import com.example.exchangerates.presentation.dialog.calculateBottomFragment.BottomSheetViewModel
import com.example.exchangerates.presentation.fragment.monetaryFragment.MonetaryRateMenuFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MonetaryRateMenuFragmentViewModel::class)
    internal abstract fun bindMonetaryRateMenuFragmentViewModel(monetaryRateMenuFragmentViewModel: MonetaryRateMenuFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BottomSheetViewModel::class)
    internal abstract fun bindBottomSheetViewModel(bottomSheetViewModel: BottomSheetViewModel): ViewModel


    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}