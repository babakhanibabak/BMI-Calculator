package com.example.bmicalculator.data.bmi

import com.example.bmicalculator.domain.bmi.BmiCalculator
import com.example.bmicalculator.domain.model.Gender
import javax.inject.Inject
import kotlin.math.pow

class BmiCalculatorImpl @Inject constructor() : BmiCalculator {
    override fun calculateBmi(weight: Double, height: Double): Double {
        if (weight <= 0 || height <= 0) return 0.0
        return weight / (height / 100).pow(2)
    }

    override fun calculateIdealWeight(height: Double, gender: Gender): Double {
        if (height <= 0) return 0.0
        return if (gender == Gender.MALE) {
            50 + 0.91 * (height - 152.4)
        } else {
            45.5 + 0.91 * (height - 152.4)
        }
    }

    override fun calculateBodyFat(bmi: Double, age: Int, gender: Gender): Double {
        if (bmi <= 0 || age <= 0) return 0.0
        return if (gender == Gender.MALE) {
            1.20 * bmi + 0.23 * age - 16.2
        } else {
            1.20 * bmi + 0.23 * age - 5.4
        }
    }
}