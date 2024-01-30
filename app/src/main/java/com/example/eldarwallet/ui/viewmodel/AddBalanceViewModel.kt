package com.example.eldarwallet.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eldarwallet.EldarWalletApp
import com.example.eldarwallet.domain.model.User
import com.example.eldarwallet.domain.use_case.AddBalanceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddBalanceViewModel @Inject constructor(
    private val addBalanceUseCase: AddBalanceUseCase,

    ) : ViewModel() {
    val isLoading = MutableLiveData<Boolean>()
    val isSuccess = MutableLiveData<Boolean>()

    fun addBalance(addBlanaceValue: String, user: User) {
        viewModelScope.launch {
            val id = EldarWalletApp.prefs.getId()
            isLoading.postValue(true)

            val result = addBalanceUseCase(user, addBlanaceValue)

            isSuccess.postValue(result)
            isLoading.postValue(false)

        }
    }
}