package com.example.eldarwallet.ui.view.login

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.eldarwallet.core.extensions.loseFocusAfterAction
import com.example.eldarwallet.databinding.ActivitySignInBinding
import com.example.eldarwallet.core.helpers.ValidateInputsHelper
import com.example.eldarwallet.ui.view.dashboard.DashboardActivity
import com.example.eldarwallet.ui.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private val loginViewModel: LoginViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListeners()
        initObservers()
    }
    private fun initObservers() {
        loginViewModel.isLoading.observe(this) {
            binding.progressBar.isVisible = it
        }

        loginViewModel.isSingInSuccessful.observe(this) {
            if (it) {
                startActivity(Intent(this, DashboardActivity::class.java))
            }else{
                Toast.makeText(this, "El usuario no es válido", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initListeners() {
        binding.etLoginEmail.loseFocusAfterAction(EditorInfo.IME_ACTION_NEXT)
        binding.etLoginPassword.loseFocusAfterAction(EditorInfo.IME_ACTION_DONE)

        binding.btnGoToSignUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        binding.btnLogin.setOnClickListener {
            if (validateForm()) {
                loginViewModel.signInUser(binding.etLoginEmail.text.toString(), binding.etLoginPassword.text.toString())
            }
        }


    }

    private fun validateForm(): Boolean {
        val emailMessage =
            ValidateInputsHelper.validateEmailInput(binding.etLoginEmail.text.toString())
        val passwordMessage =
            ValidateInputsHelper.validatePasswordInput(binding.etLoginPassword.text.toString())

        return showErrorMessageText( emailMessage, passwordMessage)
    }

    private fun showErrorMessageText(
        emailMessage: String,
        passwordMessage: String,
    ): Boolean {
        binding.tvEmailErrorMsg.text = emailMessage
        binding.tvPasswordErrorMsg.text = passwordMessage
        return  emailMessage.isEmpty() && passwordMessage.isEmpty()
    }


}