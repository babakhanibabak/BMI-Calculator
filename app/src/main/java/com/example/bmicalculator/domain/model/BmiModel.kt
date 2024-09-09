package com.example.bmicalculator.domain.model

data class BmiModel(
    val age: Float,
    val weight: Float,
    val height: Float,
    val gender:String,
    val bmi: Float,
    val idealWeight:Float,
    val bodyFat:Float,

    )
data class BmiModelResult(
    val bmi:Float,
    val bmiCategory: String
)
