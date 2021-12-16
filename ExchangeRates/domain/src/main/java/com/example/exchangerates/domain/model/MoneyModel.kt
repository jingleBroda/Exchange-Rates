package com.example.exchangerates.domain.model

data class MoneyModel(
    val ID:String,
    val NumCode:String,
    val CharCode:String,
    val Nominal:Int,
    val Name:String,
    val Value:Double,
    val Previous:Double
)