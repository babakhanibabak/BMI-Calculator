package com.example.bmicalculator.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.bmicalculator.R

@Composable
fun getCategories(bmi: Double?): String {
    return when {
        bmi == null -> ""
        bmi < 18.5 -> stringResource(id = R.string.underweight)
        bmi in 18.5..24.9 -> stringResource(id = R.string.normal_weight)
        bmi in 25.0..29.9 -> stringResource(id = R.string.overweight)
        bmi in 30.0..34.9 -> stringResource(id = R.string.obese_class_i)
        bmi in 35.0..39.9 -> stringResource(id = R.string.obese_class_ii)
        else -> stringResource(id = R.string.obese_class_iii)
    }
}

@Composable
fun getCategoryColor(category: String): Color {
    return when (category) {
        stringResource(id = R.string.underweight) -> Color.Yellow
        stringResource(id = R.string.normal_weight) -> Color.Green
        stringResource(id = R.string.overweight) -> Color.Cyan
        stringResource(id = R.string.obese_class_i) -> Color.Red
        stringResource(id = R.string.obese_class_ii) -> Color.Magenta
        stringResource(id = R.string.obese_class_iii) -> Color.Black
        else -> Color.Gray
    }
}


