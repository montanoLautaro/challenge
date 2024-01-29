package com.example.eldarwallet.domain.use_case

import com.example.eldarwallet.EldarWalletApp.Companion.prefs
import com.example.eldarwallet.data.repository.UserRepository
import com.example.eldarwallet.domain.model.User
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(email: String, password: String): Boolean {
        val user = repository.getUserByEmail(email)
        if (user != null) {
            if (validatePassword(user, password)) {
                user.id?.let { prefs.saveId(it) }
                val fullname = "${user.lastName} ${user.name}"
                prefs.saveFullName(fullname)
                return true
            }
        }
        return false
    }

    private fun validatePassword(user: User, password: String): Boolean {
        return user.password == password
    }
}