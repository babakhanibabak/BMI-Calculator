package com.example.bmicalculator.ui.component

import android.graphics.Color
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.wear.compose.material.Colors
import androidx.wear.compose.material.Text
import com.example.bmicalculator.R
import kotlin.math.pow

@Composable
fun calculateBmi(height: Double?, weight: Double?): Double? {
    if (height == null || weight == null) return null
    return weight / (height / 100).pow(2)
}

@Composable
fun calculateIdealWeight(height: Double?, gender: String): Double? {
    if (height == null) return null
    return if (gender == "Male") {
        50 + 0.91 * (height - 152.4)
    } else {
        45.5 + 0.91 * (height - 152.4)
    }
}

@Composable
fun calculateBodyFat(bmi: Double?, age: Int?, gender: String): Double? {
    if (bmi == null || age == null) return null
    return if (gender == "Male") {
        1.20 * bmi + 0.23 * age - 16.2
    } else {
        1.20 * bmi + 0.23 * age - 5.4
    }

}

@Composable
fun getCategories (bmi: Double?):String {
    return when{
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
fun getCategoryColor(category: String): Int {
    return when (category) {
        stringResource(id = R.string.underweight) -> Color.YELLOW
       stringResource(id = R.string.normal_weight) -> Color.GREEN
     stringResource(id = R.string.overweight) -> Color.CYAN
       stringResource(id = R.string.obese_class_i) -> Color.RED
        stringResource(id = R.string.obese_class_ii) -> Color.MAGENTA
        stringResource(id = R.string.obese_class_iii) -> Color.BLACK
        else -> Color.GRAY
    }
}


