package com.example.bmicalculator.ui.history

import com.example.bmicalculator.domain.model.BmiModel

data class BmiHistoryUiModel(
    val height: Double,
    val weight: Double,
    val bmi: Double,
    val idealWeight: Double,
    val bodyFat: Double,
)

fun BmiModel.toUiModel() = BmiHistoryUiModel(
    height = height,
    weight = weight,
    bmi = bmi,
    idealWeight = idealWeight,
    bodyFat = bodyFat,
)