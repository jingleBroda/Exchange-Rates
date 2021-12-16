package com.example.exchangerates.domain.usecase

import com.example.exchangerates.domain.model.MoneyRoomModel
import com.example.exchangerates.domain.model.ResulteApiModel
import com.example.exchangerates.domain.repository.RepositoryExchangeRates
import io.reactivex.rxjava3.core.Single

class MakeApiUseCase(private val repository: RepositoryExchangeRates) {

    fun doIt(): Single<ResulteApiModel> {
       return repository.makeApi()
    }

}