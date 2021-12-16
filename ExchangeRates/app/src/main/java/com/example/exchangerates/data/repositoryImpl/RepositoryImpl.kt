package com.example.exchangerates.data.repositoryImpl

import com.example.exchangerates.data.retrofite.RetrofiteService
import com.example.exchangerates.data.room.ExchangeRatesRoomDao
import com.example.exchangerates.data.entity.MoneyRoomModelEntity
import com.example.exchangerates.domain.model.MoneyRoomModel
import com.example.exchangerates.domain.model.ResulteApiModel
import com.example.exchangerates.domain.repository.RepositoryExchangeRates
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    val dbDao:ExchangeRatesRoomDao,
    val retroService: RetrofiteService
):RepositoryExchangeRates(){

    override fun makeDbSpecificData(
        charCode: String
    ): Single<MoneyRoomModel> {

        val zaprosSpecificDataDb = dbDao.getSpecificCurrencyCours(charCode = charCode).map(
            MoneyRoomModelEntity::toMoneyRoomModel
        )
        return zaprosSpecificDataDb //as Single<MoneyRoomModel>

    }

    override fun makeSingleInsertDbData(data: MoneyRoomModel): Completable {

        val zaprosSingleInsertDbData = dbDao.insertCurrency(
            MoneyRoomModelEntity.toMoneyRoomModelEntity(data)
        )
        return zaprosSingleInsertDbData
    }

    override fun makeDeleteSingeDataInfoDb(charCode: String): Completable {
        val zaprosDeleteSingeDataInfoDb = dbDao.deleteSingleData(charCode)
        return zaprosDeleteSingeDataInfoDb
    }

    override fun makeApi(): Single<ResulteApiModel> {
        val zaprosApi = retroService.getActualMoney()
        return zaprosApi
    }

    override fun makeDbData(): Single<List<MoneyRoomModel>> {
        val zaprosGetAllDb = dbDao.getCurrencyCours().map(
            MoneyRoomModelEntity::toMoneyRoomModelList
        )
        return zaprosGetAllDb //as Single<List<MoneyRoomModel>>
    }

}