package com.example.bmicalculator.domain.repository

import com.example.bmicalculator.domain.model.UserModel

interface UserRepository {
    suspend fun getUsersCount(): Int
    suspend fun getAllUsers(): List<UserModel>
    suspend fun insertUser(user: UserModel): Long
    suspend fun deleteUser(user: UserModel)
}