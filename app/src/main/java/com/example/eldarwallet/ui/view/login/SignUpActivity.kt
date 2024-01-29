package com.example.eldarwallet.ui.view.login

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.eldarwallet.core.extensions.loseFocusAfterAction
import com.example.eldarwallet.core.helpers.ValidateInputsHelper
import com.example.eldarwallet.databinding.ActivitySignUpBinding
import com.example.eldarwallet.domain.model.User
import com.example.eldarwallet.ui.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListeners()
        initObservers()
    }

    private fun initObservers() {
        loginViewModel.isLoading.observe(this) {
            binding.progressBar.isVisible = it
        }

        loginViewModel.isSignUpSuccessful.observe(this) {
            if (it) {
                Toast.makeText(this, "Usuario creado con éxito", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, SignInActivity::class.java))
            }
        }
    }

    private fun initListeners() {
        binding.etName.loseFocusAfterAction(EditorInfo.IME_ACTION_NEXT)
        binding.etLastName.loseFocusAfterAction(EditorInfo.IME_ACTION_NEXT)
        binding.etSignupEmail.loseFocusAfterAction(EditorInfo.IME_ACTION_NEXT)
        binding.etSignUpPassword.loseFocusAfterAction(EditorInfo.IME_ACTION_DONE)

        binding.btnSignUp.setOnClickListener {
            if (validateForm()) {

                loginViewModel.createUser(getUserFromForm())
            }
        }

        binding.btnGoToSignIn.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }


    }

    private fun validateForm(): Boolean {

        val nameMessage = ValidateInputsHelper.validateNameTextInput(binding.etName.text.toString())
        val lastNameMessage =
            ValidateInputsHelper.validateLastNameTextInput(binding.etLastName.text.toString())
        val emailMessage =
            ValidateInputsHelper.validateEmailInput(binding.etSignupEmail.text.toString())
        val passwordMessage =
            ValidateInputsHelper.validatePasswordInput(binding.etSignUpPassword.text.toString())

        return showErrorMessageText(nameMessage, lastNameMessage, emailMessage, passwordMessage)
    }

    private fun showErrorMessageText(
        nameMessage: String,
        lastNameMessage: String,
        emailMessage: String,
        passwordMessage: String,
    ): Boolean {
        binding.tvNameErrorMsg.text = nameMessage
        binding.tvLastNameErrorMsg.text = lastNameMessage
        binding.tvEmailErrorMsg.text = emailMessage
        binding.tvPasswordErrorMsg.text = passwordMessage
        return nameMessage.isEmpty() && lastNameMessage.isEmpty() && emailMessage.isEmpty() && passwordMessage.isEmpty()
    }

    private fun getUserFromForm(): User {
        val name = binding.etName.text.toString()
        val lastName = binding.etName.text.toString()
        val email = binding.etSignupEmail.text.toString()
        val password = binding.etSignUpPassword.text.toString()
        val balance = "0.00"
        return User(null, email, password, name, lastName, balance, emptyList())
    }


}