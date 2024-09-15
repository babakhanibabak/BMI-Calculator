package com.example.bmicalculator.data.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bmicalculator.data.datasource.database.dao.BmiDao
import com.example.bmicalculator.data.datasource.database.entity.BmiEntity

@Database(
    entities = [BmiEntity::class],
    version = 1,
    exportSchema = true,
    autoMigrations = [],
)
abstract class BmiDatabase : RoomDatabase() {
    abstract fun bmiDao(): BmiDao
}