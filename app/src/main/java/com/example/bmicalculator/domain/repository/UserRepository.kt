package com.example.bmicalculator.domain.repository

import com.example.bmicalculator.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun userByIdFlow(userId: Long): Flow<UserModel?>
    suspend fun getUserById(userId: Long): UserModel?
    suspend fun getUsersCount(): Int
    suspend fun getAllUsers(): List<UserModel>
    suspend fun insertUser(user: UserModel): Long
    suspend fun deleteUser(user: UserModel)
}