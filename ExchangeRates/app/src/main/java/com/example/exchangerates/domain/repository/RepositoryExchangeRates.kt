package com.example.exchangerates.domain.repository

import com.example.exchangerates.domain.model.MoneyRoomModel
import com.example.exchangerates.domain.model.ResulteApiModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

abstract class RepositoryExchangeRates {

    abstract fun makeDbSpecificData(
        charCode:String
    ):Single<MoneyRoomModel>

    abstract fun makeSingleInsertDbData(data: MoneyRoomModel):Completable

    abstract fun makeDeleteSingeDataInfoDb(charCode:String):Completable

    abstract fun makeApi(): Single<ResulteApiModel>

    abstract fun makeDbData():Single<List<MoneyRoomModel>>
}