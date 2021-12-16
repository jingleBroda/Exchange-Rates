package com.example.exchangerates.domain.usecase


import com.example.exchangerates.domain.model.MoneyRoomModel
import com.example.exchangerates.domain.repository.RepositoryExchangeRates
import io.reactivex.rxjava3.core.Single

class MakeDbDataUseCase(private val repository: RepositoryExchangeRates) {
    fun doIt(): Single<List<MoneyRoomModel>> {
        return repository.makeDbData()
    }
}