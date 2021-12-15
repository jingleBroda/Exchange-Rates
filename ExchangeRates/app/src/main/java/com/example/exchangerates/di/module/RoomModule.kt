package com.example.exchangerates.di.module

import androidx.room.Room
import com.example.exchangerates.App
import com.example.exchangerates.data.room.ExchangeRatesRoomDB
import com.example.exchangerates.data.room.ExchangeRatesRoomDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

// возможно на этапе clear architecture придется его делать абстрактным
@Module
class RoomModule() {

    @Singleton
    @Provides
    fun createDB(app: App):ExchangeRatesRoomDB{
        return Room.databaseBuilder(app,
            ExchangeRatesRoomDB::class.java,
            "exchangeRatesDB")
            .build()
    }

    @Singleton
    @Provides
    fun getDAO(db:ExchangeRatesRoomDB):ExchangeRatesRoomDao{
        return db.databaseDao()
    }

}