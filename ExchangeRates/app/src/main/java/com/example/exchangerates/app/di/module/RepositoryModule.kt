package com.example.exchangerates.app.di.module

import com.example.exchangerates.data.repositoryImpl.RepositoryImpl
import com.example.exchangerates.data.retrofite.RetrofiteService
import com.example.exchangerates.data.room.ExchangeRatesRoomDao
import com.example.exchangerates.domain.repository.RepositoryExchangeRates
import com.example.exchangerates.domain.usecase.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun makeRepository(
        dbDao: ExchangeRatesRoomDao,
        retroService: RetrofiteService
    ):RepositoryExchangeRates{
        return RepositoryImpl(dbDao, retroService)
    }

    @Provides
    fun makeDbDataUseCase(
        repository:RepositoryExchangeRates
    ): MakeDbDataUseCase {
        return MakeDbDataUseCase(repository)
    }

    @Provides
    fun makeDbSpecificUseCase(
        repository:RepositoryExchangeRates
    ):MakeDbSpecificDataUseCase{
        return MakeDbSpecificDataUseCase(repository)
    }

    @Provides
    fun makeDeleteSingeDataInfoDbUseCase(
        repository:RepositoryExchangeRates
    ):MakeDeleteSingeDataInfoDbUseCase{
        return MakeDeleteSingeDataInfoDbUseCase(repository)
    }

    @Provides
    fun makeSingleInsertDbDataUseCase(
        repository:RepositoryExchangeRates
    ):MakeSingleInsertDbDataUseCase{
        return MakeSingleInsertDbDataUseCase(repository)
    }

    @Provides
    fun makeApiUseCase(
        repository:RepositoryExchangeRates
    ):MakeApiUseCase{
        return MakeApiUseCase(repository)
    }
}