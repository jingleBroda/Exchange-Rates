package com.example.exchangerates.presentation.fragment.monetaryFragment

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.exchangerates.App
import com.example.exchangerates.data.retrofite.RetrofiteService
import com.example.exchangerates.data.room.ExchangeRatesRoomDao
import com.example.exchangerates.domain.model.MoneyRoomModel
import com.example.exchangerates.domain.model.ResulteApiModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MonetaryRateMenuFragmentViewModel(application: Application): AndroidViewModel(application) {

    private val retroService: RetrofiteService = (application as App).getRetroService()
    private val compositeDisposable = CompositeDisposable()
    private val dbDao: ExchangeRatesRoomDao = (application as App).getDbDao()

    private var obserInGetAllDataDb: ((data: List<MoneyRoomModel>)->Unit)?=null
    private var obserInDataApi: ((data: ResulteApiModel)->Unit)?=null


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

    fun cleareCompositeDisposable(){
        compositeDisposable.clear()
    }

}