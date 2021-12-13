package com.example.exchangerates.data.retrofite

import com.example.exchangerates.domain.model.ResulteApiModel
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface RetrofiteService {
    @GET("https://www.cbr-xml-daily.ru/daily_json.js")
    fun getActualMoney():Single<ResulteApiModel>

}