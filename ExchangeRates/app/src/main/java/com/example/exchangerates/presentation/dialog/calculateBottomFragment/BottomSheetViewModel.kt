package com.example.exchangerates.presentation.dialog.calculateBottomFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exchangerates.domain.model.MoneyRoomModel
import com.example.exchangerates.domain.usecase.MakeDbSpecificDataUseCase
import com.example.exchangerates.domain.usecase.MakeDeleteSingeDataInfoDbUseCase
import com.example.exchangerates.domain.usecase.MakeSingleInsertDbDataUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class BottomSheetViewModel @Inject constructor(
    private val makeDbSpecificDataUseCase: MakeDbSpecificDataUseCase,
    private val makeSingleInsertDbDataUseCase: MakeSingleInsertDbDataUseCase,
    private val makeDeleteSingeDataInfoDbUseCase: MakeDeleteSingeDataInfoDbUseCase
    ): ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _specificDataDb = MutableSharedFlow<MoneyRoomModel>()
    val specificDataDb:SharedFlow<MoneyRoomModel> = _specificDataDb.asSharedFlow()

    fun makeDbSpecificData(charCode:String){
       viewModelScope.launch {
           _specificDataDb.emit(makeDbSpecificDataUseCase.doIt(charCode))
       }
    }

    fun makeSingleInsertDbData(data: MoneyRoomModel){
        val zaprosSingleInsertDbData = makeSingleInsertDbDataUseCase.doIt(data)
            .subscribeOn(Schedulers.single())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()

        compositeDisposable.add(zaprosSingleInsertDbData)
    }


    fun makeDeleteSingeDataInfoDb(charCode:String){
        val zaprosDeleteSingeDataInfoDb = makeDeleteSingeDataInfoDbUseCase.doIt(charCode)
            .subscribeOn(Schedulers.single())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()

        compositeDisposable.add(zaprosDeleteSingeDataInfoDb)
    }

    fun clearCompositeDisposable(){
        compositeDisposable.clear()
    }
}