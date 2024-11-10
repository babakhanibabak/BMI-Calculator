package com.example.bmicalculator.ui.bmi

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

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
    val BmiCircleColor:Color
)
