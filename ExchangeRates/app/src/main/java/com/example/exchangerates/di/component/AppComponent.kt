package com.example.exchangerates.di.component

import com.example.exchangerates.App
import com.example.exchangerates.di.module.*
import com.example.exchangerates.presentation.fragment.monetaryFragment.MonetaryRateMenuFragmentViewModel
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(modules = [
    AndroidSupportInjectionModule::class,
    AndroidInjectionModule::class,
    FragmentModule::class,
    DialogModule::class,
    RetrofiteModule::class,
    RoomModule::class,
    ViewModelModule::class
])

@Singleton
interface AppComponent:AndroidInjector<App> {

    override fun inject(app: App)


    @Component.Builder
    interface Builder{
        @BindsInstance
        fun bindContext(app:App):Builder

        fun build():AppComponent
    }

}