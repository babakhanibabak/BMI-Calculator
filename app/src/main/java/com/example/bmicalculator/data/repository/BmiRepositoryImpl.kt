package com.example.bmicalculator.data.repository

import com.example.bmicalculator.data.datasource.database.dao.BmiDao
import com.example.bmicalculator.data.datasource.database.entity.BmiCalculationResult
import com.example.bmicalculator.data.datasource.database.entity.BmiEntity
import com.example.bmicalculator.data.datasource.database.entity.toDomainModel
import com.example.bmicalculator.domain.model.BmiModel
import com.example.bmicalculator.domain.model.BmiModelResult
import com.example.bmicalculator.domain.repository.BmiRepository
import javax.inject.Inject

class BmiRepositoryImpl @Inject constructor(
    private val bmiDao: BmiDao
) : BmiRepository {
override suspend fun getBmiModel():BmiModel{
    return bmiDao.getAllBmi().toDomainModel()}

    suspend fun insertBmiModel(bmiModel: BmiModel) {
        val entity=BmiEntity(
            age = bmiModel.age,
            height = bmiModel.height,
            weight = bmiModel.weight
        )
        bmiDao.insertBmiData(
            entity
        )
    }



    override suspend fun saveBmiModel(bmiModelResult: BmiModelResult) {
        val entityResult=BmiCalculationResult(
            bmiCategory = bmiModelResult.bmiCategory,
            bmi = bmiModelResult.bmi
        )


    }
}
