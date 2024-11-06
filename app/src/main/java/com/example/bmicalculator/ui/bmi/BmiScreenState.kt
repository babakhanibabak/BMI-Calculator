package com.example.bmicalculator.ui.bmi

import com.example.bmicalculator.domain.model.Gender

data class BmiScreenState(
    val userId: Long = 0,
    val fullName: String = "",
    val gender: Gender = Gender.MALE,
    val weight: String = "",
    val height: String = "",
    val age: String = "",
    val bmi: String? = "--",
    val idealWeight: String? = "--",
    val bodyFat: String? = "--",
    val bmiClassifications: List<BmiBmiClassificationItem>? = null,
)