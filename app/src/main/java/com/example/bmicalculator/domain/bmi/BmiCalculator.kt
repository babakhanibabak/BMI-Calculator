package com.example.bmicalculator.domain.bmi

import com.example.bmicalculator.domain.model.Gender

interface BmiCalculator {
    fun calculateBmi(weight: Double, height: Double): Double
    fun calculateIdealWeight(height: Double, gender: Gender): Double
    fun calculateBodyFat(bmi: Double, age: Int, gender: Gender): Double
}