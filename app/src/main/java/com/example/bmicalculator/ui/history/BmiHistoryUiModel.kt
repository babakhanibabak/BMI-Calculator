package com.example.bmicalculator.ui.history

import androidx.compose.ui.graphics.Color
import com.example.bmicalculator.data.extensions.formatBmiValue
import com.example.bmicalculator.domain.model.BmiModel
import com.example.bmicalculator.ui.bmi.calculate.BmiClassificationMapper

data class BmiHistoryUiModel(
    val height: Double,
    val weight: Double,
    val bmi: String,
    val idealWeight: String,
    val bodyFat: String,
    val backGroundColor: Color = Color.Transparent,
)

fun BmiModel.toUiModel() = BmiHistoryUiModel(
    height = 0.0,
    weight = 0.0,
    bmi = bmi.formatBmiValue(),
    idealWeight = idealWeight.formatBmiValue(),
    bodyFat = bodyFat.formatBmiValue(),
    backGroundColor = BmiClassificationMapper.getClassificationColor(bmi)
)