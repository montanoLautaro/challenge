package com.example.eldarwallet.data.network

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class GenerateQrService @Inject constructor(
    private val api: GenerateQrCodeApiClient
) {
    private val contentType = "application/x-www-form-urlencoded"
    private val apiHost = "neutrinoapi-qr-code.p.rapidapi.com"
    private val apiKey: String = "84c10046bdmshab98f71bc03eaecp1200eejsn33e76c85a8f0"


    suspend fun generateQrCode(content: String): Bitmap? {
        return withContext(Dispatchers.IO) {
            try {

                val response = api.generateQR(contentType, apiKey, apiHost, content)

                Log.d("RESPONSE", " $response")
                Log.d("GENERATEQR", "${response.raw()}")
                Log.d("GENERATEQR", "${response.body()}")
                Log.d("GENERATEQR", "${response.errorBody()}")
                // Convierte el cuerpo de la respuesta a Bitmap si es necesario
                // Manejar la respuesta exitosa aquí
                val responseBody = response.body()
                // Convierte el cuerpo de la respuesta a Bitmap si es necesario
                BitmapFactory.decodeStream(responseBody?.byteStream())


            } catch (e: Exception) {
                Log.d("generateQrCode", "Exception on generateQrCode $e")
                null
            }
        }
    }


}