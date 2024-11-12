package com.example.bmicalculator.data.datasource.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bmicalculator.data.datasource.database.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserEntity): Long

    @Query(SELECT_BY_ID)
    suspend fun getUserById(id: Long): UserEntity?

    @Query(SELECT_BY_ID)
    fun userByIdFlow(id: Long): Flow<UserEntity?>

    @Query(SELECT_ALL)
    suspend fun getAllUsers(): List<UserEntity>

    @Query(SELECT_ALL_COUNT)
    suspend fun getUsersCount(): Int

    @Delete
    suspend fun delete(user: UserEntity)

    companion object {
        private const val SELECT_BY_ID = "SELECT * FROM users WHERE user_id = :id"
        private const val SELECT_ALL = "SELECT * FROM users"
        private const val SELECT_ALL_COUNT = "SELECT COUNT(*) FROM users"

    }
}