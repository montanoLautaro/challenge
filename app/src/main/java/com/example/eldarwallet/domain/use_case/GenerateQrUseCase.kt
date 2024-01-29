package com.example.eldarwallet.domain.use_case

import android.graphics.Bitmap
import com.example.eldarwallet.data.repository.GenerateQrRepository
import javax.inject.Inject

class GenerateQrUseCase @Inject constructor(
    private val repository: GenerateQrRepository
) {
    suspend operator fun invoke(fullName: String): Bitmap? {
        return repository.generateQrCode(fullName)
    }
}