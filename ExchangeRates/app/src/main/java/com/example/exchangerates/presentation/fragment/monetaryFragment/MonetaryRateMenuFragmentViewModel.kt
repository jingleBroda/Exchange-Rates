package com.example.exchangerates.presentation.fragment.monetaryFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exchangerates.domain.model.MoneyRoomModel
import com.example.exchangerates.domain.model.ResulteApiModel
import com.example.exchangerates.domain.usecase.MakeApiUseCase
import com.example.exchangerates.domain.usecase.MakeDbDataUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MonetaryRateMenuFragmentViewModel @Inject constructor(
    private val makeApiUseCase: MakeApiUseCase,
    private val makeDbDataUseCase: MakeDbDataUseCase,
    ): ViewModel() {

    private var jobViewModel:Job? = null

    private val _dataApi = MutableSharedFlow<ResulteApiModel>()
    val dataApi:SharedFlow<ResulteApiModel> = _dataApi.asSharedFlow()

    private val _dbData = MutableSharedFlow<List<MoneyRoomModel>>()
    val dbData:SharedFlow<List<MoneyRoomModel>> = _dbData.asSharedFlow()

    fun makeApi(){
        jobViewModel = viewModelScope.async {
            _dataApi.emit(makeApiUseCase.doIt())

        }
        jobViewModel!!.start()
    }

    fun cancelCoroutine() {
        jobViewModel!!.cancel()
    }

    fun makeDbData(){
        viewModelScope.launch {
            _dbData.emit(makeDbDataUseCase.doIt())
        }
    }
}