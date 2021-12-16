package com.example.exchangerates.data.room

import androidx.room.*
import com.example.exchangerates.data.entity.MoneyRoomModelEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface ExchangeRatesRoomDao {
    @Query("SELECT * FROM elect_currency")
    fun getCurrencyCours(): Single<List<MoneyRoomModelEntity>>

    @Query("SELECT * FROM elect_currency WHERE charCodeId = :charCode")
    fun getSpecificCurrencyCours(charCode:String): Single<MoneyRoomModelEntity>

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrency(electCurrency: MoneyRoomModelEntity) :Completable

    @Query("DELETE FROM elect_currency")
    fun deleteAllData():Completable

    @Query("DELETE FROM elect_currency WHERE charCodeId = :charCode")
    fun deleteSingleData(charCode:String):Completable
}