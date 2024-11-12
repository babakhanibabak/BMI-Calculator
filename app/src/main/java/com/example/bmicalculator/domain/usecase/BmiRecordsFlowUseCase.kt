package com.example.bmicalculator.domain.usecase

import com.example.bmicalculator.domain.model.BmiModel
import com.example.bmicalculator.domain.repository.BmiRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BmiRecordsFlowUseCase @Inject constructor(
    private val repository: BmiRepository,
) {
    fun execute(userId: Long): Flow<List<BmiModel>> {
        return repository.getAllRecords(userId)
    }
}