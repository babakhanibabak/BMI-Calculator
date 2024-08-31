package com.example.bmicalculator.data.datasource.database.di

import android.content.Context
import androidx.room.Room
import com.example.bmicalculator.data.datasource.database.BmiDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideBmiDatabase(
        @ApplicationContext context: Context,
    ): BmiDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = BmiDatabase::class.java,
            name = "BMI",
        ).build()
    }
}