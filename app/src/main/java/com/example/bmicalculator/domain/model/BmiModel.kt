package com.example.bmicalculator.domain.model

data class BmiModel(
    val age: Int,
    val weight: Double,
    val height: Double,
    val gender: Gender,
    val bmi: Double,
    val idealWeight: Double,
    val bodyFat: Double,
)
