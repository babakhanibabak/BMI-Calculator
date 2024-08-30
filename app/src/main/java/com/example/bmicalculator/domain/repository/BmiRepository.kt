package com.example.bmicalculator.domain.repository

import com.example.bmicalculator.domain.model.BmiModel

interface BmiRepository {
    suspend fun getBmiModel(): BmiModel
    suspend fun saveBmiModel(bmiModel: BmiModel)

}