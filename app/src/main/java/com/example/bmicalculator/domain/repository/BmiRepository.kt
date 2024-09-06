package com.example.bmicalculator.domain.repository

import com.example.bmicalculator.data.datasource.database.entity.BmiEntity
import com.example.bmicalculator.domain.model.BmiModel
import com.example.bmicalculator.domain.model.BmiModelResult
import kotlinx.coroutines.flow.Flow

interface BmiRepository {
    suspend fun insert( record:BmiEntity)
    suspend fun getAllRecords():Flow<List<BmiEntity>>

}