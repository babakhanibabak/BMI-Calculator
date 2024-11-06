package com.example.bmicalculator.data

import com.example.bmicalculator.data.bmi.BmiCalculatorImpl
import com.example.bmicalculator.data.repository.BmiRepositoryImpl
import com.example.bmicalculator.data.repository.UserRepositoryImpl
import com.example.bmicalculator.domain.bmi.BmiCalculator
import com.example.bmicalculator.domain.repository.BmiRepository
import com.example.bmicalculator.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    @Singleton
    fun bindBmiRepository(impl: BmiRepositoryImpl): BmiRepository

    @Binds
    @Singleton
    fun bindUserRepository(impl: UserRepositoryImpl): UserRepository

    @Binds
    @Singleton
    fun bindBmiCalculator(impl: BmiCalculatorImpl): BmiCalculator
}