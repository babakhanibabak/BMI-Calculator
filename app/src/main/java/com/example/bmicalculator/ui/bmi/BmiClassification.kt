package com.example.bmicalculator.ui.bmi

import androidx.compose.ui.graphics.Color

enum class BmiClassification {
    UNDER_WEIGHT,
    NORMAL,
    OVER_WEIGHT,
    OBESE_CLASS_I,
    OBESE_CLASS_II,
    OBESE_CLASS_III,
    UNKNOWN;
}

data class BmiBmiClassificationItem(
    val title: String,
    val color: Color,
    val backgroundColor: Color,
)
