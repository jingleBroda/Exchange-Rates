package com.example.exchangerates.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "elect_currency")
data class MoneyRoomModel(
    @PrimaryKey
    val charCodeId:String,
    val coursCurrencyInRUB:Double,
    val lockStatus:Boolean
)