package com.example.exchangerates.domain.usecase

import com.example.exchangerates.domain.model.MoneyRoomModel
import com.example.exchangerates.domain.repository.RepositoryExchangeRates
import io.reactivex.rxjava3.core.Completable

class MakeSingleInsertDbDataUseCase (private val repository: RepositoryExchangeRates) {
    fun doIt(data:MoneyRoomModel): Completable {
        return repository.makeSingleInsertDbData(data)
    }
}