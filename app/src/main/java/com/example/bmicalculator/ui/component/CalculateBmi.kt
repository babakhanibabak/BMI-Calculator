package com.example.bmicalculator.ui.component

import androidx.compose.runtime.Composable
import kotlin.math.pow

@Composable
fun calculateBmi(height: Float?, weight: Float?): Float? {
    if (height == null || weight == null) return null
    return weight / (height / 100).pow(2)
}

@Composable
fun calculateIdealWeight(height: Float?, gender: String): Double? {
    if (height == null) return null
    return if (gender == "Male") {
        50 + 0.91 * (height - 152.4)
    } else {
        45.5 + 0.91 * (height - 152.4)
    }
}

@Composable
fun calculateBodyFat(bmi: Float?, age: Int?, gender: String): Double? {
    if (bmi == null || age == null) return null
    return if (gender == "Male") {
        1.20 * bmi + 0.23 * age - 16.2
    } else {
        1.20 * bmi + 0.23 * age - 5.4
    }
}
