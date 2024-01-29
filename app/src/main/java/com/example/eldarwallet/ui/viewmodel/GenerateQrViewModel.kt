package com.example.eldarwallet.ui.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eldarwallet.EldarWalletApp
import com.example.eldarwallet.domain.use_case.GenerateQrUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenerateQrViewModel @Inject constructor(
    private val generateQrUseCase: GenerateQrUseCase
) : ViewModel() {
    val isLoading = MutableLiveData<Boolean>()
    val qr = MutableLiveData<Bitmap?>()

    fun generateQr() {
        viewModelScope.launch {
            val fullName = EldarWalletApp.prefs.getFullName()
            isLoading.postValue(true)

            val result = generateQrUseCase(fullName!!)

            qr.postValue(result)

            isLoading.postValue(false)

        }
    }
}