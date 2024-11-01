package com.example.bmicalculator.data.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bmicalculator.data.datasource.database.dao.AccountDao
import com.example.bmicalculator.data.datasource.database.dao.BmiDao
import com.example.bmicalculator.data.datasource.database.entity.BmiEntity
import com.example.bmicalculator.data.datasource.database.entity.UserEntity

@Database(
    entities = [BmiEntity::class, UserEntity::class],
    version = 1,
    exportSchema = true,
    autoMigrations = [],
)
abstract class BmiDatabase : RoomDatabase() {
    abstract fun bmiDao(): BmiDao
    abstract fun accountDao():AccountDao
}