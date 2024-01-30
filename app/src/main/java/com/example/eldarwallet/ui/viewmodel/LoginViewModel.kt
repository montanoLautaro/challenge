package com.example.eldarwallet.ui.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eldarwallet.domain.use_case.SignInUseCase
import com.example.eldarwallet.domain.use_case.SignUpUseCase
import com.example.eldarwallet.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val signInUseCase: SignInUseCase,
) : ViewModel() {
    val isLoading = MutableLiveData<Boolean>()
    val isSignUpSuccessful = MutableLiveData<Boolean>()
    val isSingInSuccessful = MutableLiveData<Boolean>()

    fun createUser(user: User) {
        viewModelScope.launch {
            isLoading.postValue(true)

            val result = signUpUseCase(user)
            isLoading.postValue(false)

            isSignUpSuccessful.postValue(result)
        }
    }

    fun signInUser(email: String, password: String) {
        viewModelScope.launch {
            isLoading.postValue(true)

            val result = signInUseCase(email, password)
            isLoading.postValue(false)

            isSingInSuccessful.postValue(result)
        }
    }
}