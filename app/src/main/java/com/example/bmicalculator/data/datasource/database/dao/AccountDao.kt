package com.example.bmicalculator.data.datasource.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bmicalculator.data.datasource.database.entity.UserEntity

@Dao
interface AccountDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAccount(account: UserEntity)

    @Query("SELECT * FROM User WHERE name = :username AND family = :password")
    suspend fun getAccountByUsernameAndPassword(username: String, password: String): UserEntity?
}