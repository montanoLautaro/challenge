package com.example.eldarwallet.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.eldarwallet.domain.model.Card

@Entity(tableName = "card_table")
data class CardEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_card") val idCard: Long = 0,
    @ColumnInfo(name = "card_brand") val cardBrand: String?,
    @ColumnInfo(name = "pan") val pan: String?,
    @ColumnInfo(name = "card_holder") val cardHolder: String?,
    @ColumnInfo(name = "expiration_date") val expirationDate: String?,
    @ColumnInfo(name = "cvc_code") val cvcCode: String?,
    @ColumnInfo(name = "user_id") val userId: Long?,
)


fun Card.toDatabase(userId: Long?) = CardEntity(
    cardBrand = cardBrand,
    pan = pan,
    cardHolder = cardHolder,
    expirationDate = expirationDate,
    cvcCode = cvcCode,
    userId = userId
)
