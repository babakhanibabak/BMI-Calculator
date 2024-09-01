package com.example.bmicalculator.domain.repository

import com.example.bmicalculator.domain.model.BmiModel
import com.example.bmicalculator.domain.model.BmiModelResult

interface BmiRepository {
    suspend fun getBmiModel(): BmiModel
    suspend fun saveBmiModel(bmiModelResult: BmiModelResult)

}