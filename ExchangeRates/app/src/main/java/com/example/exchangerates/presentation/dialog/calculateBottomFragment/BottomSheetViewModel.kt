package com.example.exchangerates.presentation.dialog.calculateBottomFragment

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.exchangerates.data.room.ExchangeRatesRoomDao
import com.example.exchangerates.domain.model.MoneyRoomModel
import com.example.exchangerates.domain.usecase.MakeDbSpecificDataUseCase
import com.example.exchangerates.domain.usecase.MakeDeleteSingeDataInfoDbUseCase
import com.example.exchangerates.domain.usecase.MakeSingleInsertDbDataUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class BottomSheetViewModel @Inject constructor(
    //dbDaoInside: ExchangeRatesRoomDao
    val makeDbSpecificDataUseCase: MakeDbSpecificDataUseCase,
    val makeSingleInsertDbDataUseCase: MakeSingleInsertDbDataUseCase,
    val makeDeleteSingeDataInfoDbUseCase: MakeDeleteSingeDataInfoDbUseCase
    ): ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    //private val dbDao = dbDaoInside

    private var obserInGetSpecificDataDb: ((data: MoneyRoomModel)->Unit)?=null

    fun makeDbSpecificData(charCode:String){
        val zaprosSpecificDataDb = makeDbSpecificDataUseCase.doIt(charCode)
            .subscribeOn(Schedulers.single())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Log.d("GetDbError", it.toString())
                    obserInGetSpecificDataDb?.invoke(it)
                },
                {
                    val emptyDataRoom = MoneyRoomModel(
                        "",
                        0.0,
                        false
                    )
                    obserInGetSpecificDataDb?.invoke(emptyDataRoom)
                    Log.d("GetDbErrorM", it.toString())
                }
            )



        compositeDisposable.add(zaprosSpecificDataDb)
    }

    fun getDBSpecificData(code:(data: MoneyRoomModel)->Unit){
        obserInGetSpecificDataDb = code
    }

    fun makeSingleInsertDbData(data: MoneyRoomModel){
        val zaprosSingleInsertDbData = makeSingleInsertDbDataUseCase.doIt(data)
            .subscribeOn(Schedulers.single())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Log.d("GetDbDataNew", data.toString())
                },
                {

                }
            )



        compositeDisposable.add(zaprosSingleInsertDbData)
    }


    fun makeDeleteSingeDataInfoDb(charCode:String){
        val zaprosDeleteSingeDataInfoDb = makeDeleteSingeDataInfoDbUseCase.doIt(charCode)
            .subscribeOn(Schedulers.single())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()

        compositeDisposable.add(zaprosDeleteSingeDataInfoDb)
    }

    fun cleareCompositeDisposable(){
        compositeDisposable.clear()
    }

}
