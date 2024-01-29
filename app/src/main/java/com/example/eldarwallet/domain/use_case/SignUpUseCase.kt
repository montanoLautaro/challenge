package com.example.eldarwallet.domain.use_case

import com.example.eldarwallet.EldarWalletApp.Companion.prefs
import com.example.eldarwallet.data.repository.UserRepository
import com.example.eldarwallet.domain.model.User
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(newUser: User): Boolean {
        val id = repository.createUser(newUser)
        if (id != null && id >= 0) {
            prefs.saveId(id)
            return true
        }
        return false
    }
}