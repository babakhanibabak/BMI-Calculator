package com.example.bmicalculator.data.repository

import com.example.bmicalculator.data.datasource.database.dao.BmiDao
import com.example.bmicalculator.data.datasource.database.entity.toDomain
import com.example.bmicalculator.data.datasource.database.entity.toEntity
import com.example.bmicalculator.domain.model.BmiModel
import com.example.bmicalculator.domain.repository.BmiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BmiRepositoryImpl @Inject constructor(
    private val bmiDao: BmiDao
) : BmiRepository {

    override suspend fun insert(record: BmiModel) {
        bmiDao.insertBmiData(record.toEntity())
    }

    override fun getAllRecords(userId: Long): Flow<List<BmiModel>> {
        return bmiDao.getBmiForUserFlow(userId).map { records ->
            records.map { it.toDomain() }
        }
    }
}
