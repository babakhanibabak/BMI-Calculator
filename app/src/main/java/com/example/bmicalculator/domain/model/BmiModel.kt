package com.example.bmicalculator.domain.model

data class BmiModel(
    val age: Double,
    val weight: Double,
    val height: Double,
    val gender:String,
    val bmi: Double,
    val idealWeight:Double,
    val bodyFat:Double,

    )
data class BmiModelResult(
    val bmi:Float,
    val bmiCategory: String
)
