package com.example.eldarwallet.data.database.entities

import androidx.room.Embedded
import androidx.room.Relation
import com.example.eldarwallet.domain.model.User

data class UserWhitCards(
    @Embedded val user: UserEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "user_id",
        entity = CardEntity::class
    )
    val cards: List<CardEntity> = emptyList()
)

fun User.toDatabaseRelationated(id: Long) = UserWhitCards(
    user = UserEntity(
        email = email, password = password, name = name, balance = balance, lastName = lastName
    ),
    cards = cards.map { it.toDatabase(id) },
)


fun User.toDatabase() = UserEntity(
    email = email, password = password, name = name, balance = balance, lastName = lastName
)

fun User.toDatabaseWhitId() = UserEntity(
    email = email, password = password, name = name, balance = balance, lastName = lastName, id = id!!
)
