package com.example.eldarwallet.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.eldarwallet.data.database.entities.CardEntity
import com.example.eldarwallet.data.database.entities.UserEntity
import com.example.eldarwallet.data.database.entities.UserWhitCards

@Dao
interface EldarWalletDao {
    @Transaction
    @Query("SELECT * FROM user_table")
    suspend fun getAllUsers(): List<UserWhitCards>


    @Transaction
    @Query("SELECT * FROM user_table  WHERE id = :id")
    suspend fun getUserAllDataById(id: Long): UserWhitCards?

    @Transaction
    @Query("SELECT * FROM user_table WHERE email LIKE :email  LIMIT 1 ")
    suspend fun getUserByEmail(email: String): UserWhitCards?


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCard(card: CardEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun deleteCard(card: CardEntity)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateUser(userEntity: UserEntity): Int


}