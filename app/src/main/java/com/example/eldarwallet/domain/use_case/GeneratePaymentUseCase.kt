package com.example.eldarwallet.domain.use_case

import com.example.eldarwallet.data.repository.UserRepository
import com.example.eldarwallet.domain.model.User
import javax.inject.Inject

class GeneratePaymentUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(
        user: User,
        paymentValue: String
    ): String {
        if (validatePayment(user.balance, paymentValue)) {
            val newBalance = user.balance.toDouble() - paymentValue.toDouble()

            val newUser = User(
                user.id,
                user.email,
                user.password,
                user.name,
                user.lastName,
                newBalance.toString(),
                user.cards
            )
            val result = repository.generatePayment(newUser)
            if (result) {
                return "Pago realizado con éxito"
            }
        }

        return "Saldo insuficiente"
    }

    private fun validatePayment(balance: String, paymentValue: String): Boolean {
        val balanceValue = balance.toDouble()
        val paymentValueDouble = paymentValue.toDouble()
        return (balanceValue > paymentValueDouble)

    }
}