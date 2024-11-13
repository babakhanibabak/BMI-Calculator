package com.example.bmicalculator.ui.bmi.result

import com.example.bmicalculator.ui.bmi.BmiClassificationItem

sealed class BmiResultScreenState {
    data object Loading : BmiResultScreenState()
    data class Error(val message: String) : BmiResultScreenState()
    data class Success(
        val weight: String = "",
        val height: String = "",
        val age: String = "",
        val bmiValue: Double = 0.0,
        val formattedBmiValue: String? = "--",
        val idealWeight: String? = "--",
        val bodyFat: String? = "--",
        val bmiClassifications: List<BmiClassificationItem>? = null,
    ) : BmiResultScreenState()
}
