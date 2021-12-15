package com.example.exchangerates.presentation.dialog.calculateBottomFragment

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.exchangerates.data.room.ExchangeRatesRoomDao
import com.example.exchangerates.domain.model.MoneyRoomModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class BottomSheetViewModel @Inject constructor(
    dbDaoInside: ExchangeRatesRoomDao
    ): ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val dbDao = dbDaoInside

    private var obserInGetSpecificDataDb: ((data: MoneyRoomModel)->Unit)?=null

    fun makeDbSpecificData(charCode:String){
        val zaprosSpecificDataDb = dbDao.getSpecificCurrencyCours(charCode = charCode)
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
        val zaprosSingleInsertDbData = dbDao.insertCurrency(data)
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
        val zaprosDeleteSingeDataInfoDb = dbDao.deleteSingleData(charCode)
            .subscribeOn(Schedulers.single())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()

        compositeDisposable.add(zaprosDeleteSingeDataInfoDb)
    }

    fun cleareCompositeDisposable(){
        compositeDisposable.clear()
    }

}
