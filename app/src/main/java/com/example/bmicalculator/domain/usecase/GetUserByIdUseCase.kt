package com.example.bmicalculator.domain.usecase

import com.example.bmicalculator.domain.repository.UserRepository
import javax.inject.Inject

class GetUserByIdUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    suspend fun execute(userId: Long) = userRepository.getUserById(userId)
}