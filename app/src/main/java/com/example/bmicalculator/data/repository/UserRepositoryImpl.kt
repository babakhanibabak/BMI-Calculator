package com.example.bmicalculator.data.repository

import com.example.bmicalculator.data.datasource.database.dao.UserDao
import com.example.bmicalculator.data.datasource.database.entity.toDomain
import com.example.bmicalculator.data.datasource.database.entity.toEntity
import com.example.bmicalculator.domain.model.UserModel
import com.example.bmicalculator.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
) : UserRepository {

    override suspend fun getUsersCount(): Int {
        return userDao.getUsersCount()
    }

    override suspend fun getAllUsers(): List<UserModel> {
        return userDao.getAllUsers().map { it.toDomain() }
    }

    override suspend fun insertUser(user: UserModel): Long {
        return userDao.insert(user.toEntity())
    }

    override suspend fun deleteUser(user: UserModel) {
        userDao.delete(user = user.toEntity())
    }
}