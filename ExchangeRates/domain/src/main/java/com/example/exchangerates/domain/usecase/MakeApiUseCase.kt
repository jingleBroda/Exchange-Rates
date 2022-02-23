package com.example.exchangerates.domain.usecase

import com.example.exchangerates.domain.model.ResulteApiModel
import com.example.exchangerates.domain.repository.RepositoryExchangeRates

class MakeApiUseCase(private val repository: RepositoryExchangeRates) {
    suspend fun doIt():ResulteApiModel {
       return repository.makeApi()
    }
}