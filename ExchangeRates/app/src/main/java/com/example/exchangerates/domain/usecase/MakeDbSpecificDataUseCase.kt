package com.example.exchangerates.domain.usecase

import com.example.exchangerates.domain.model.MoneyRoomModel
import com.example.exchangerates.domain.repository.RepositoryExchangeRates
import io.reactivex.rxjava3.core.Single

class MakeDbSpecificDataUseCase(private val repository: RepositoryExchangeRates) {
    fun doIt(charCode:String): Single<MoneyRoomModel> {
        return repository.makeDbSpecificData(charCode)
    }
}