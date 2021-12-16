package com.example.exchangerates.domain.model

data class ResulteApiModel(
    var Date:String,
    var PreviousDate:String,
    var PreviousURL:String,
    var Timestamp:String,
    var Valute:MutableMap<String, MoneyModel>
)