package com.example.bmicalculator.domain.usecase

import com.example.bmicalculator.domain.model.BmiModel
import com.example.bmicalculator.domain.repository.BmiRepository
import javax.inject.Inject

class InsertBmiUseCase @Inject constructor(
    private val bmiRepository: BmiRepository,
) {
    suspend fun execute(record: BmiModel) {
        bmiRepository.insert(record)
    }
}