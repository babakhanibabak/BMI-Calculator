package com.example.bmicalculator.data.datasource.database.di

import com.example.bmicalculator.data.datasource.database.BmiDatabase
import com.example.bmicalculator.data.datasource.database.dao.UserDao
import com.example.bmicalculator.data.datasource.database.dao.BmiDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {
    @Provides
    @Singleton
    fun provideBmiDataDao(db: BmiDatabase): BmiDao {
        return db.bmiDao()
    }

    @Provides
    @Singleton
    fun provideUserDao(db: BmiDatabase): UserDao {
        return db.userDao()
    }
}