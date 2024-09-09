package com.example.bmicalculator.data.repository

import com.example.bmicalculator.data.datasource.database.dao.BmiDao
import com.example.bmicalculator.data.datasource.database.entity.BmiEntity
import com.example.bmicalculator.domain.model.BmiModel
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

    override suspend fun getAllRecords(): Flow<List<BmiEntity>> = flow {
        emit(bmiDao.getAllBmi())
    }
    private fun BmiEntity.toDomainModel(): BmiModel {
        return BmiModel(
            age = this.age,
            height = this.height,
            weight = this.weight,
            gender = this.gender,
            bmi = this.bmi,
            idealWeight = this.idealWeight,
            bodyFat = this.bodyFat
        )
    }
}

