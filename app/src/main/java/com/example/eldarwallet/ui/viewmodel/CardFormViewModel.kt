package com.example.eldarwallet.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eldarwallet.EldarWalletApp
import com.example.eldarwallet.domain.model.Card
import com.example.eldarwallet.domain.use_case.AddCardUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardFormViewModel @Inject constructor(
    private val addCardUseCase: AddCardUseCase
) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val isSuccess = MutableLiveData<Boolean>()

    fun addCard(card: Card) {
        viewModelScope.launch {
            val id = EldarWalletApp.prefs.getId()
            isLoading.postValue(true)

            val result = addCardUseCase(card, id)

            isSuccess.postValue(result)

            isLoading.postValue(false)

        }
    }

}