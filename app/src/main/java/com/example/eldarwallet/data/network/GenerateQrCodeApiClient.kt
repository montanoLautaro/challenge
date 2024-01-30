package com.example.eldarwallet.data.network

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface GenerateQrCodeApiClient {

    @POST("qr-code")
    @FormUrlEncoded
    suspend fun generateQR(
        @Field("content-type") contentType: String,
        @Field("X-RapidAPI-Key") apiKey: String,
        @Field("X-RapidAPI-Host") apiHost: String,
        @Field("content") content: String
    ): Response<ResponseBody>

}
