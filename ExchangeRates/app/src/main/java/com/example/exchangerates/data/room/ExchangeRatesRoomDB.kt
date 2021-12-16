package com.example.exchangerates.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.exchangerates.data.entity.MoneyRoomModelEntity

@Database(entities = [MoneyRoomModelEntity::class], version = 1, exportSchema = true)
abstract class ExchangeRatesRoomDB:RoomDatabase() {
    abstract fun databaseDao():ExchangeRatesRoomDao
}