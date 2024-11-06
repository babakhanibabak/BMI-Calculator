package com.example.bmicalculator.domain.model

data class BmiModel(
    val userId: Long,
    val age: Int,
    val height: Double,
    val weight: Double,
    val bmi: Double,
    val idealWeight: Double,
    val bodyFat: Double,
)
