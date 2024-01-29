package com.example.eldarwallet.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long = 0,
    @ColumnInfo(name = "email") val email: String?,
    @ColumnInfo(name = "password") val password: String?,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "last_name") val lastName: String?,
    @ColumnInfo(name = "balance" ) val balance: String?,
)
