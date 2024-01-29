package com.example.eldarwallet.data.repository


import android.graphics.Bitmap
import com.example.eldarwallet.data.network.GenerateQrService
import javax.inject.Inject

class GenerateQrRepository @Inject constructor(
    private val api: GenerateQrService
) {
    suspend fun generateQrCode(name: String):Bitmap?{
        return api.generateQrCode(name)

    }
}