package com.example.bmicalculator.domain.usecase

import com.example.bmicalculator.domain.repository.UserRepository
import javax.inject.Inject

class GetUsersCountUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    suspend fun execute() = userRepository.getUsersCount()
}