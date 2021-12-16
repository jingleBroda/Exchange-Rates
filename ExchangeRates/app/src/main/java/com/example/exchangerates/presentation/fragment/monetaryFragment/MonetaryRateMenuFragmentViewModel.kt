package com.example.exchangerates.presentation.fragment.monetaryFragment

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.exchangerates.data.entity.MoneyRoomModelEntity
import com.example.exchangerates.domain.model.MoneyRoomModel
import com.example.exchangerates.domain.model.ResulteApiModel
import com.example.exchangerates.domain.usecase.MakeApiUseCase
import com.example.exchangerates.domain.usecase.MakeDbDataUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class MonetaryRateMenuFragmentViewModel @Inject constructor(
    val makeApiUseCase: MakeApiUseCase,
    val makeDbDataUseCase: MakeDbDataUseCase
    ): ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private var obserInGetAllDataDb: ((data: List<MoneyRoomModel>)->Unit)?=null
    private var obserInDataApi: ((data: ResulteApiModel)->Unit)?=null

    fun makeApi(){
        val zaprosMakeApi = makeApiUseCase.doIt()
            .subscribeOn(Schedulers.single())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {result->
                    obserInDataApi?.invoke(result)

                },
                {
                    Log.d("APIError", it.toString())
                }
            )

        compositeDisposable.add(zaprosMakeApi)
    }

    fun getApiData(code:((data: ResulteApiModel)->Unit)){
        obserInDataApi = code
    }

    fun makeDbData(){
        val zaprosGetAllDb = makeDbDataUseCase.doIt()
            .subscribeOn(Schedulers.single())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    obserInGetAllDataDb?.invoke(it)
                },
                {
                    val emptyMoneyRoomListEntity:List<MoneyRoomModel> = arrayListOf()
                    obserInGetAllDataDb?.invoke(emptyMoneyRoomListEntity)
                    Log.d("GetDbError", it.toString())
                }
            )



        compositeDisposable.add(zaprosGetAllDb)
    }

    fun getDbData(code:(data: List<MoneyRoomModel>)->Unit){
        obserInGetAllDataDb = code
    }

    fun cleareCompositeDisposable(){
        compositeDisposable.clear()
    }

}

/*
fun makeApi(){
        val zaprosApi = retroService.getActualMoney()
            .subscribeOn(Schedulers.single())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {result->
                    obserInDataApi?.invoke(result)

                },
                {
                    Log.d("APIError", it.toString())
                }
            )

        compositeDisposable.add(zaprosApi)
    }

    fun getApiData(code:((data: ResulteApiModel)->Unit)){
        obserInDataApi = code
    }

    fun makeDbData(){
        val zaprosGetAllDb = dbDao.getCurrencyCours()
            .subscribeOn(Schedulers.single())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    obserInGetAllDataDb?.invoke(it)
                },
                {
                    val emptyMoneyRoomList:List<MoneyRoomModel> = arrayListOf()
                    obserInGetAllDataDb?.invoke(emptyMoneyRoomList)
                    Log.d("GetDbError", it.toString())
                }
            )



        compositeDisposable.add(zaprosGetAllDb)
    }

    fun getDbData(code:(data: List<MoneyRoomModel>)->Unit){
        obserInGetAllDataDb = code
    }
 */