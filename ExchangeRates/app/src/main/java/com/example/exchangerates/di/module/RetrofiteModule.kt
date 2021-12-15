package com.example.exchangerates.di.module

import com.example.exchangerates.data.retrofite.RetrofiteService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

// возможно на этапе clear architecture придется его делать абстрактным
@Module
class RetrofiteModule() {
    private val baseUrl = "https://www.cbr-xml-daily.ru/"

    @Singleton
    @Provides
    fun createRetrofite():Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun createRetrofiteService(retrofit: Retrofit):RetrofiteService{
        return retrofit.create(RetrofiteService::class.java)
    }


}