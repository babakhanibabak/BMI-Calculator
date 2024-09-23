package com.example.bmicalculator.ui.bmi

import com.example.bmicalculator.domain.model.Gender

data class BmiScreenState(
    val gender: Gender = Gender.MALE,
    val weight: String = "",
    val height: String = "",
    val age: String = "",
    val bmi: String? = null,
    val idealWeight: String? = null,
    val bodyFat: String? = null,
    val bmiClassifications: List<BmiBmiClassificationItem>? = null,
)