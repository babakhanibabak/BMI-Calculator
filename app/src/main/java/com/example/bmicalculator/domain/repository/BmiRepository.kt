package com.example.bmicalculator.domain.repository

import com.example.bmicalculator.data.datasource.database.entity.BmiEntity
import kotlinx.coroutines.flow.Flow

interface BmiRepository {
    suspend fun insert( record:BmiEntity)
    suspend fun getAllRecords():Flow<List<BmiEntity>>
}