package com.example.bmicalculator.data.datasource.database.di

import com.example.bmicalculator.data.datasource.database.BmiDatabase
import com.example.bmicalculator.data.datasource.database.dao.BmiDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {
    @Provides
    fun provideLocationDao(db: BmiDatabase) : BmiDao {
        return db.bmiDao()
    }
}