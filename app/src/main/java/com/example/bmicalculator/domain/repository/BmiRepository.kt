package com.example.bmicalculator.domain.repository

import com.example.bmicalculator.domain.model.BmiModel
import kotlinx.coroutines.flow.Flow

interface BmiRepository {
    suspend fun insert(record: BmiModel)
    fun getAllRecords(userId: Long): Flow<List<BmiModel>>
}