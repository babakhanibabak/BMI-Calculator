package com.example.bmicalculator.domain.usecase

import com.example.bmicalculator.domain.repository.UserRepository
import javax.inject.Inject

class UserByIdFlowUseCase @Inject constructor(
    private val repository: UserRepository,
) {
    fun execute(userId: Long) = repository.userByIdFlow(userId)
}