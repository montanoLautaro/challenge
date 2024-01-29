package com.example.eldarwallet.di

import android.content.Context
import androidx.room.Room
import com.example.eldarwallet.data.database.EldarWalletDatabase
import com.example.eldarwallet.data.database.dao.EldarWalletDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    private const val ELDAR_WALLET_DATABASE_NAME = "eldar_wallet_database"

    @Singleton
    @Provides
    fun roomProvider(@ApplicationContext context: Context) = Room.databaseBuilder(context, EldarWalletDatabase::class.java,ELDAR_WALLET_DATABASE_NAME ).build()

    @Singleton
    @Provides
    fun eldarWalletDaoProvider(db: EldarWalletDatabase) = db.getEldarWalletDao()
}