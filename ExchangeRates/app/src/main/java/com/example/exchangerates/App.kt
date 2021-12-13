package com.example.exchangerates

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.exchangerates.data.retrofite.RetrofiteService
import com.example.exchangerates.data.room.ExchangeRatesRoomDB
import com.example.exchangerates.data.room.ExchangeRatesRoomDao
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class App: Application() {

    private val baseUrl = "https://www.cbr-xml-daily.ru/"
    private lateinit var retrofit: Retrofit
    private lateinit var retrofiteService: RetrofiteService

    private lateinit var db:ExchangeRatesRoomDB

    companion object
    {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()

        retrofit =Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

        retrofiteService = retrofit.create(RetrofiteService::class.java)

        db = Room.databaseBuilder(this, ExchangeRatesRoomDB::class.java, "exchangeRatesDB").build()

        instance = this

    }

    fun getRetroService(): RetrofiteService {
        return retrofiteService
    }

    fun getDbDao():ExchangeRatesRoomDao{
        return db.databaseDao()
    }

}