package com.example.bmicalculator.domain.usecase

import com.example.bmicalculator.domain.bmi.BmiCalculator
import javax.inject.Inject

class GetBmiUseCase @Inject constructor(
    private val bmiCalculator: BmiCalculator,
) {
    fun execute(weight: Double, height: Double) = bmiCalculator.calculateBmi(weight, height)
}