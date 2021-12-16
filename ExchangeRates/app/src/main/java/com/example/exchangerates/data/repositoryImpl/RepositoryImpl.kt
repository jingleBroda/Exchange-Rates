package com.example.exchangerates.data.repositoryImpl

import android.util.Log
import com.example.exchangerates.data.retrofite.RetrofiteService
import com.example.exchangerates.data.room.ExchangeRatesRoomDao
import com.example.exchangerates.domain.model.MoneyRoomModel
import com.example.exchangerates.domain.model.ResulteApiModel
import com.example.exchangerates.domain.repository.RepositoryExchangeRates
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    val dbDao:ExchangeRatesRoomDao,
    val retroService: RetrofiteService
):RepositoryExchangeRates(){

    override fun makeDbSpecificData(
        charCode: String
    ): Single<MoneyRoomModel> {

        val zaprosSpecificDataDb = dbDao.getSpecificCurrencyCours(charCode = charCode)
        return zaprosSpecificDataDb

    }

    override fun makeSingleInsertDbData(data: MoneyRoomModel): Completable {
        val zaprosSingleInsertDbData = dbDao.insertCurrency(data)
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
        val zaprosGetAllDb = dbDao.getCurrencyCours()
        return zaprosGetAllDb
    }

}