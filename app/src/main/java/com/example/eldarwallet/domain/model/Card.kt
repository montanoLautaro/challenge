package com.example.eldarwallet.domain.model

import com.example.eldarwallet.core.helpers.EncryptionHelper
import com.example.eldarwallet.data.database.entities.CardEntity


data class Card(
    val cardBrand: String,
    val pan: String,
    val cardHolder: String,
    val expirationDate: String,
    val cvcCode: String,
    var isSelected: Boolean = false
)

//todo corregir los assert
fun CardEntity.toDomain() = Card(
    EncryptionHelper.decrypt(cardBrand!!),
    EncryptionHelper.decrypt(pan!!),
    EncryptionHelper.decrypt(cardHolder!!),
    EncryptionHelper.decrypt(expirationDate!!),
    EncryptionHelper.decrypt(cvcCode!!),

    )