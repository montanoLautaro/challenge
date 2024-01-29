package com.example.eldarwallet.data.network

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Streaming

interface GenerateQrCodeApiClient {

    //todo cambiar API KEY
    @FormUrlEncoded
    @Headers(
        "X-RapidAPI-Key: 1a43a4602cmshd8004b5c7794191p1a788fjsn5a0c3d021639",
        "X-RapidAPI-Host: neutrinoapi-qr-code.p.rapidapi.com",
        "Content-Type: application/x-www-form-urlencoded"
    )
    @POST("qr-code")
    @Streaming
    suspend fun generateQR(
        @Field("content") content: String,
        @Field("width") width: String,
        @Field("height") height: String,

    ): Call<ResponseBody>

}