package com.example.exchangerates.domain.repository

import com.example.exchangerates.domain.model.MoneyRoomModel
import com.example.exchangerates.domain.model.ResulteApiModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

abstract class RepositoryExchangeRates {

    abstract suspend fun makeApi():ResulteApiModel

    abstract suspend fun makeDbData():List<MoneyRoomModel>

    abstract suspend fun makeDbSpecificData(
        charCode:String
    ):MoneyRoomModel

    abstract fun makeSingleInsertDbData(data: MoneyRoomModel):Completable

    abstract fun makeDeleteSingeDataInfoDb(charCode:String):Completable
}