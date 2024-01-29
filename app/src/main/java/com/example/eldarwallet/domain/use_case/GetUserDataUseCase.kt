package com.example.eldarwallet.domain.use_case

import com.example.eldarwallet.data.repository.UserRepository
import com.example.eldarwallet.domain.model.User
import javax.inject.Inject

class GetUserDataUseCase @Inject constructor(private val repository: UserRepository) {
    suspend operator fun invoke(id: Long): User? {
        return repository.getUserAllDataById(id)
    }
}