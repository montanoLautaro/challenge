package com.example.eldarwallet.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eldarwallet.EldarWalletApp.Companion.prefs
import com.example.eldarwallet.domain.model.User
import com.example.eldarwallet.domain.use_case.GetUserDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserDataUseCase: GetUserDataUseCase
) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val userData = MutableLiveData<User?>()



    fun onCreate() {
        viewModelScope.launch {
            val id = prefs.getId()
            isLoading.postValue(true)

            val result = getUserDataUseCase(id)


            userData.postValue(result)

            isLoading.postValue(false)

        }
    }
}