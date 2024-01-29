package com.example.eldarwallet.domain.use_case

import com.example.eldarwallet.data.repository.UserRepository
import com.example.eldarwallet.domain.model.Card
import javax.inject.Inject

class AddCardUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(card: Card, userId: Long): Boolean {
        return repository.addCard(card, userId)
    }
}