package com.example.eldarwallet.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eldarwallet.EldarWalletApp.Companion.prefs
import com.example.eldarwallet.domain.model.Card
import com.example.eldarwallet.domain.model.User
import com.example.eldarwallet.domain.use_case.GeneratePaymentUseCase
import com.example.eldarwallet.domain.use_case.GetUserDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getUserDataUseCase: GetUserDataUseCase,
    private val generatePaymentUseCase: GeneratePaymentUseCase
) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val userData = MutableLiveData<User?>()
    val generatePaymentResult = MutableLiveData<String>()


    fun onCreate() {
        viewModelScope.launch {
            val id = prefs.getId()
            isLoading.postValue(true)

            val result = getUserDataUseCase(id)


            userData.postValue(result)

            isLoading.postValue(false)

        }
    }

    fun generatePayment(user: User, paymentValue: String, card: Card) {
        viewModelScope.launch {
            val id = prefs.getId()
            isLoading.postValue(true)

            val result = generatePaymentUseCase.invoke(user, paymentValue)

            generatePaymentResult.postValue(result)


            isLoading.postValue(false)

        }
    }


}