package com.example.exchangerates.domain.usecase

import com.example.exchangerates.domain.repository.RepositoryExchangeRates
import io.reactivex.rxjava3.core.Completable

class MakeDeleteSingeDataInfoDbUseCase(private val repository: RepositoryExchangeRates) {
    fun doIt(charCode:String):Completable{
        return repository.makeDeleteSingeDataInfoDb(charCode)
    }
}