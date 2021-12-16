package com.example.exchangerates.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.exchangerates.domain.model.MoneyRoomModel

@Entity(tableName = "elect_currency")
data class MoneyRoomModelEntity(
    @PrimaryKey
    val charCodeId:String,
    val coursCurrencyInRUB:Double,
    val lockStatus:Boolean
) {
    fun toMoneyRoomModel() = MoneyRoomModel(
        charCodeId,
        coursCurrencyInRUB,
        lockStatus
    )

    companion object {
        fun toMoneyRoomModelEntity(data: MoneyRoomModel) =
            data.run {
                MoneyRoomModelEntity(
                    charCodeId,
                    coursCurrencyInRUB,
                    lockStatus
                )
            }
        fun toMoneyRoomModelList(entitiesList: List<MoneyRoomModelEntity>) =
            entitiesList.map(MoneyRoomModelEntity::toMoneyRoomModel)
    }
}