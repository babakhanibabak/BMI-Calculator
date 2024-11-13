package com.example.bmicalculator.ui.bmi.calculate

import com.example.bmicalculator.domain.model.Gender

data class BmiCalculateScreenState(
    val userId: Long = 0,
    val fullName: String = "",
    val gender: Gender = Gender.MALE,
    val weight: String = "",
    val height: String = "",
    val age: String = "",
)