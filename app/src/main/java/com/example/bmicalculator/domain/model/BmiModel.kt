package com.example.bmicalculator.domain.model

data class BmiModel(
    val age:Int,
    val weight:Int ,
    val height:Int
)
data class BmiModelResult(
    val bmi:Float,
    val bmiCategory: String
)
