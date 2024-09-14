package com.example.bmicalculator.domain.usecase

import com.example.bmicalculator.domain.bmi.BmiCalculator
import com.example.bmicalculator.domain.model.Gender
import javax.inject.Inject

class GetIdealWeightUseCase @Inject constructor(
    private val bmiCalculator: BmiCalculator,
) {
    fun execute(height: Double, gender: Gender) = bmiCalculator.calculateIdealWeight(height, gender)
}