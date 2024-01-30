package com.example.eldarwallet.data.repository

import android.database.sqlite.SQLiteException
import android.util.Log
import com.example.eldarwallet.core.helpers.EncryptionHelper
import com.example.eldarwallet.data.database.dao.EldarWalletDao
import com.example.eldarwallet.data.database.entities.CardEntity
import com.example.eldarwallet.data.database.entities.toDatabase
import com.example.eldarwallet.data.database.entities.toDatabaseWhitId
import com.example.eldarwallet.domain.model.Card
import com.example.eldarwallet.domain.model.User
import com.example.eldarwallet.domain.model.toDomain
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val dao: EldarWalletDao
) {
    suspend fun createUser(newUser: User): Long? {
        try {
            return dao.insertUser(newUser.toDatabase())
        } catch (e: SQLiteException) {
            Log.d("exception", "SQLiteException on createUser: ${e.cause} ")
            Log.d("exception", "SQLiteException on createUser: ${e.message} ")
        } catch (e: Exception) {
            Log.d("exception", "Exception on createUser: ${e.cause} ")
            Log.d("exception", "Exception on createUser: ${e.message} ")
        }
        return null
    }

    suspend fun getUserByEmail(email: String): User? {
        try {
            val user = dao.getUserByEmail(email)
            if (user != null) {
                return user.toDomain()
            }
        } catch (e: SQLiteException) {
            Log.d("exception", "SQLiteException on getUserByEmail: ${e.cause} ")
            Log.d("exception", "SQLiteException on getUserByEmail: ${e.message} ")
        } catch (e: Exception) {
            Log.d("exception", "Exception on getUserByEmail: ${e.cause} ")
            Log.d("exception", "Exception on getUserByEmail: ${e.message} ")
        }
        return null
    }

    suspend fun getUserAllDataById(id: Long): User? {
        try {
            val user = dao.getUserAllDataById(id)
            if (user != null) {

                return user.toDomain()
            }
        } catch (e: SQLiteException) {
            Log.d("exception", "SQLiteException on getUserAllDataById: ${e.cause} ")
            Log.d("exception", "SQLiteException on getUserAllDataById: ${e.message} ")
        } catch (e: Exception) {
            Log.d("exception", "Exception on getUserAllDataById: ${e.cause} ")
            Log.d("exception", "Exception on getUserAllDataById: ${e.message} ")
        }
        return null
    }

    suspend fun addCard(card: Card, userId: Long): Boolean {
        try {

            val id = dao.insertCard(getCard(card, userId))
            if (id >= 0) {
                return true
            }
        } catch (e: SQLiteException) {
            Log.d("exception", "SQLiteException on addCard: ${e.cause} ")
            Log.d("exception", "SQLiteException on addCard: ${e.message} ")
        } catch (e: Exception) {
            Log.d("exception", "Exception on addCard: ${e.cause} ")
            Log.d("exception", "Exception on addCard: ${e.message} ")
        }
        return false
    }

    private fun getCard(card: Card, userId: Long): CardEntity {
        val pan = EncryptionHelper.encrypt(card.pan)
        val cardholder = EncryptionHelper.encrypt(card.cardHolder)
        val expDate = EncryptionHelper.encrypt(card.expirationDate)
        val cvc = EncryptionHelper.encrypt(card.cvcCode)
        val cardBrand = EncryptionHelper.encrypt(card.cardBrand)
        return CardEntity(
            pan = pan,
            cardHolder = cardholder,
            cardBrand = cardBrand,
            cvcCode = cvc,
            expirationDate = expDate,
            userId = userId

        )
    }

    suspend fun updateUser(user: User): Boolean {
        try {
            Log.d("updateUser", "entro")
            val id = dao.updateUser(user.toDatabaseWhitId())
            Log.d("updateUser", "$id")
            if (id > 0) {
                return true
            }
        } catch (e: SQLiteException) {
            Log.d("exception", "SQLiteException on addCard: ${e.cause} ")
            Log.d("exception", "SQLiteException on addCard: ${e.message} ")
        } catch (e: Exception) {
            Log.d("exception", "Exception on addCard: ${e.cause} ")
            Log.d("exception", "Exception on addCard: ${e.message} ")
        }
        return false
    }
}