package com.example.bmicalculator.domain.usecase

import com.example.bmicalculator.domain.model.UserModel
import com.example.bmicalculator.domain.repository.UserRepository
import javax.inject.Inject

class GetAllUsersUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    suspend fun execute(): List<UserModel> {
        return userRepository.getAllUsers()
    }
}