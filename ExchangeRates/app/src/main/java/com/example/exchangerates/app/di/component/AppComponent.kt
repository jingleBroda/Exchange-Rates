package com.example.exchangerates.app.di.component

import com.example.exchangerates.app.App
import com.example.exchangerates.app.di.module.*
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
    ViewModelModule::class,
    RepositoryModule::class
])

@Singleton
interface AppComponent:AndroidInjector<App> {

    override fun inject(app: App)


    @Component.Builder
    interface Builder{
        @BindsInstance
        fun bindContext(app: App):Builder

        fun build():AppComponent
    }

}