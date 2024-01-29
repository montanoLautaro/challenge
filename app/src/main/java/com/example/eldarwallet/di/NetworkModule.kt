package com.example.eldarwallet.di

import com.example.eldarwallet.data.network.GenerateQrCodeApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun retrofitProvider(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://neutrinoapi-qr-code.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Singleton
    @Provides
    fun apiClientProvider(retrofit: Retrofit): GenerateQrCodeApiClient {
        return retrofit.create(GenerateQrCodeApiClient::class.java)
    }

}