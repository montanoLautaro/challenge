package com.example.eldarwallet.data.network

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

import okhttp3.ResponseBody
import javax.inject.Inject

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class GenerateQrService @Inject constructor(
    private val api: GenerateQrCodeApiClient
) {
    private val qrHeight = "250"
    private val qrWidth = "250"

    suspend fun generateQrCode(content: String): Bitmap? {
        return withContext(Dispatchers.IO) {
            try {
                val deferred = CompletableDeferred<Bitmap?>()

                api.generateQR(content, qrWidth, qrHeight)
                    .enqueue(object : Callback<ResponseBody> {
                        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                            if (response.isSuccessful) {
                                Log.d("generateQrCode", "${response.body()}")

                                // Convirtiendo el cuerpo de la respuesta a Bitmap
                                val bitmap = BitmapFactory.decodeStream(response.body()?.byteStream())
                                deferred.complete(bitmap)
                            } else {
                                deferred.complete(null)
                            }
                        }

                        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                            deferred.complete(null)
                        }
                    })

                deferred.await()
            } catch (e: Exception) {
                Log.d("generateQrCode", "Exception on generateQrCode $e")
                null
            }
        }
    }




}