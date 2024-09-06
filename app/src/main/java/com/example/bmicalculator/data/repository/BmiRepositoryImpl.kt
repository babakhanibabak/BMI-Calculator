package com.example.bmicalculator.data.repository

import com.example.bmicalculator.data.datasource.database.dao.BmiDao
import com.example.bmicalculator.data.datasource.database.entity.BmiEntity
import com.example.bmicalculator.domain.repository.BmiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BmiRepositoryImpl @Inject constructor(
    private val bmiDao: BmiDao
) : BmiRepository {

    override suspend fun insert(record: BmiEntity) {
        bmiDao.insertBmiData(record)
    }

    override suspend fun getAllRecords(): Flow<List<BmiEntity>> = flow{
emit(bmiDao.getAllBmi())
    }
}

