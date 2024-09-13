package com.example.bmicalculator.ui.component

import android.graphics.Color
import androidx.compose.runtime.Composable
import kotlin.math.pow

@Composable
fun calculateBmi(height: Double?, weight: Double?): Double? {
    if (height == null || weight == null) return null
    return weight / (height / 100).pow(2)
}

@Composable
fun calculateIdealWeight(height: Double?, gender: String): Double? {
    if (height == null) return null
    return if (gender == "Male") {
        50 + 0.91 * (height - 152.4)
    } else {
        45.5 + 0.91 * (height - 152.4)
    }
}

@Composable
fun calculateBodyFat(bmi: Double?, age: Int?, gender: String): Double? {
    if (bmi == null || age == null) return null
    return if (gender == "Male") {
        1.20 * bmi + 0.23 * age - 16.2
    } else {
        1.20 * bmi + 0.23 * age - 5.4
    }

}

@Composable
fun getCategories (bmi: Double?):String {
    return when{
        bmi == null -> ""
        bmi < 18.5 -> "Underweight"
        bmi in 18.5..24.9 -> "Normal weight"
        bmi in 25.0..29.9 -> "Overweight"
        bmi in 30.0..34.9 -> " obese class I"
        bmi in 35.0..39.9 -> "obese class II"
        else -> "obese class III"
    }

}


fun getCategoryColor(category: String): Int {
    return when (category) {
        "Underweight" -> Color.YELLOW
        "Normal weight" -> Color.GREEN
        "Overweight" -> Color.CYAN
        "Obese class I" -> Color.RED
        "Obese class II" -> Color.MAGENTA
        "Obese class III" -> Color.BLACK
        else -> Color.GRAY
    }
}

