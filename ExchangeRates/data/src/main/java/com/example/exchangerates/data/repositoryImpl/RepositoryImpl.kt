package com.example.exchangerates.data.repositoryImpl

import android.util.Log
import com.example.exchangerates.data.retrofite.RetrofiteService
import com.example.exchangerates.data.room.ExchangeRatesRoomDao
import com.example.exchangerates.data.entity.MoneyRoomModelEntity
import com.example.exchangerates.domain.model.MoneyRoomModel
import com.example.exchangerates.domain.model.ResulteApiModel
import com.example.exchangerates.domain.repository.RepositoryExchangeRates
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val dbDao: ExchangeRatesRoomDao,
    private val retroService: RetrofiteService
):RepositoryExchangeRates() {

    override suspend fun makeApi(): ResulteApiModel {
        return retroService.getActualMoney()
    }

    override suspend fun makeDbData(): List<MoneyRoomModel> {
        return MoneyRoomModelEntity.toMoneyRoomModelList( dbDao.getCurrencyCours())
    }

    override suspend fun makeDbSpecificData(
        charCode: String
    ): MoneyRoomModel {
        return if(dbDao.getSpecificCurrencyCours(charCode = charCode) == null){ //проверка на то, сохранял ли пользователь валюту
            // если нет, то передаем стандартные параметры
            val emptyDataRoom = MoneyRoomModel(
                "",
                0.0,
                false
            )
            emptyDataRoom
        }
        else{
            //иначе те, которые сохранены в БД
            dbDao.getSpecificCurrencyCours(charCode = charCode).toMoneyRoomModel()
        }
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
}
