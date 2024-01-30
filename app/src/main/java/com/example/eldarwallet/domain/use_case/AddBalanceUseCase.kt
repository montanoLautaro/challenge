package com.example.eldarwallet.domain.use_case

import android.util.Log
import com.example.eldarwallet.data.repository.UserRepository
import com.example.eldarwallet.domain.model.User
import javax.inject.Inject

class AddBalanceUseCase @Inject constructor(private val repository: UserRepository) {
    suspend operator fun invoke(user: User, addBalanceValue: String): Boolean {
        val newUser = User(
            user.id,
            user.email,
            user.password,
            user.name,
            user.lastName,
            plusBalance(user, addBalanceValue),
            user.cards
        )

        Log.d("AddBalanceUseCase", "$newUser")

        return repository.updateUser(newUser)
    }

    private fun plusBalance(user: User, addBalanceValue: String): String {
        val currentBalance = user.balance.toDouble()
        val otherBalanceValue = addBalanceValue.toDouble()
        return (currentBalance + otherBalanceValue).toString()
    }
}