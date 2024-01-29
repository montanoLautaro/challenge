package com.example.eldarwallet.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.eldarwallet.data.database.dao.EldarWalletDao
import com.example.eldarwallet.data.database.entities.CardEntity
import com.example.eldarwallet.data.database.entities.UserEntity

@Database(entities = [UserEntity::class, CardEntity::class], version = 6)
abstract class EldarWalletDatabase : RoomDatabase() {
    abstract fun getEldarWalletDao(): EldarWalletDao

}