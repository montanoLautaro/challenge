package com.example.eldarwallet.domain.model

import com.example.eldarwallet.data.database.entities.UserWhitCards

data class User(
    val id:Long?,
    val email: String,
    val password : String,
    val name: String,
    val lastName: String,
    val balance: String,
    val cards: List<Card>,
)
//todo revisar los assert
fun UserWhitCards.toDomain() = User(
    id = user.id,
    email = user.email!!,
    password = user.password!!,
    name = user.name!!,
    lastName = user.lastName!!,
    balance = user.balance!!,
    cards = cards.map { it.toDomain() })