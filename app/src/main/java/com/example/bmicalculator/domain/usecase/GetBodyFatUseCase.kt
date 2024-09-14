package com.example.bmicalculator.domain.usecase

import com.example.bmicalculator.domain.bmi.BmiCalculator
import com.example.bmicalculator.domain.model.Gender
import javax.inject.Inject

class GetBodyFatUseCase @Inject constructor(
    private val bmiCalculator: BmiCalculator,
) {
    fun execute(bmi: Double, age: Int, gender: Gender) =
        bmiCalculator.calculateBodyFat(bmi, age, gender)
}